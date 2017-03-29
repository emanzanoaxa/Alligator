package com.art.alligator.exceptions;

import com.art.alligator.Command;

/**
 * Date: 26.02.2017
 * Time: 18:56
 *
 * @author Artur Artikov
 */

/**
 * Exception thrown by execute method of {@link Command}
 */
public class CommandExecutionException extends Exception {
	private Command mCommand;
	private String mReason;

	public CommandExecutionException(Command command, String reason) {
		super("Failed to execute navigation command " + command.getClass().getSimpleName() + ". " + reason);
		mCommand = command;
		mReason = reason;
	}

	public Command getCommand() {
		return mCommand;
	}

	public String getReason() {
		return mReason;
	}
}