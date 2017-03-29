package com.art.alligator.screenswitchers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.art.alligator.ScreenSwitcher;
import com.art.alligator.TransitionAnimation;

/**
 * Date: 01/30/2016
 * Time: 10:13
 *
 * @author Artur Artikov
 */

/**
 * Screen switcher that switches fragments in a given container
 */
public abstract class FragmentScreenSwitcher implements ScreenSwitcher {
	private FragmentManager mFragmentManager;
	private int mContainerId;

	public FragmentScreenSwitcher(FragmentManager fragmentManager, int containerId) {
		mFragmentManager = fragmentManager;
		mContainerId = containerId;
	}

	protected abstract Fragment createFragment(String screenName);

	protected void onScreenSwitched(String screenName) {
	}

	protected TransitionAnimation getAnimation(String screenNameFrom, String screenNameTo) {
		return TransitionAnimation.DEFAULT;
	}

	@Override
	public boolean switchTo(String screenName) {
		Fragment currentFragment = getCurrentFragment();

		Fragment newFragment = mFragmentManager.findFragmentByTag(screenName);
		boolean justCreated = newFragment == null;
		if (newFragment == null) {
			newFragment = createFragment(screenName);
			if (newFragment == null) {
				return false;
			}
		}

		if(currentFragment == newFragment) {
			return true;
		}

		FragmentTransaction transaction = mFragmentManager.beginTransaction();

		if(currentFragment != null) {
			TransitionAnimation animation = getAnimation(currentFragment.getTag(), screenName);
			animation.applyToFragmentTransaction(transaction);
			transaction.detach(currentFragment);
		}

		if(justCreated) {
			transaction.add(mContainerId, newFragment, screenName);
		} else {
			transaction.attach(newFragment);
		}

		transaction.commitNow();

		onScreenSwitched(screenName);
		return true;
	}

	public Fragment getCurrentFragment() {
		return mFragmentManager.findFragmentById(mContainerId);
	}

	public String getCurrentScreenName() {
		Fragment currentFragment = getCurrentFragment();
		return currentFragment != null ? currentFragment.getTag() : null;
	}
}