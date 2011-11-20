package de.hatoma.exman.dao.exceptions;

public class NoPreviousAttemptException extends Exception {
	/** The serial version uid. */
	private static final long serialVersionUID = -8577997971202195020L;

	public NoPreviousAttemptException() {
		super("There is no previous attempt.");
	}
}
