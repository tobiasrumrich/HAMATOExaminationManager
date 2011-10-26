package de.hatoma.exman.service.room;

/**
 * Exception that indicates a room usage.
 */
public class RoomInUseException extends RuntimeException {

	/** the serial version uid. */
	private static final long serialVersionUID = -6080027662783731790L;

	/**
	 * Constructor for {@link RoomAlreadyExistsException}.
	 */
	public RoomInUseException() {
		super("Der Raum wird noch von Veranstaltungen verwendet!");
	}

}
