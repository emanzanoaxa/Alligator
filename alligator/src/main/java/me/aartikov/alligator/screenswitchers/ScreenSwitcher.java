package me.aartikov.alligator.screenswitchers;

/**
 * Date: 22.01.2017
 * Time: 22:46
 *
 * @author Artur Artikov
 */

import android.support.annotation.Nullable;

import me.aartikov.alligator.Screen;
import me.aartikov.alligator.animations.AnimationData;
import me.aartikov.alligator.exceptions.NavigationException;

/**
 * Object for switching between several screens without theirs recreation.
 */
public interface ScreenSwitcher {
	/**
	 * Switches to a given screen.
	 *
	 * @param screen        screen
	 * @param animationData animation data for an additional animation configuring
	 * @throws me.aartikov.alligator.exceptions.NavigationException on fail
	 */
	void switchTo(Screen screen, @Nullable AnimationData animationData) throws NavigationException;

	/**
	 * Returns a current screen.
	 *
	 * @return current screen or {@code null} if there is no current screen.
	 */
	@Nullable
	Screen getCurrentScreen();
}