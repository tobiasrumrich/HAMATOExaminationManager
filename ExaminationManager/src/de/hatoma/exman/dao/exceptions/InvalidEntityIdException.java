package de.hatoma.exman.dao.exceptions;

public class InvalidEntityIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidEntityIdException() {
		super("The id provided does not match to an existing entity.");
	}
}
