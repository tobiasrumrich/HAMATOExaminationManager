package de.hatoma.exman.dao.exceptions;

public class EntityIsFrozenException extends Exception {
	/** The serial version uid. */
	private static final long serialVersionUID = -8577997971202195020L;

	
	public EntityIsFrozenException() {
		super("The entity cannot be edited since there are already references to this entity.");
	}
}
