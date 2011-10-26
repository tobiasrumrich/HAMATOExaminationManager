/**
 * 
 */
package de.hatoma.exman.service.room;

/**
 * Exception that indicates a missing room.
 */
public class RoomNotFoundException extends RuntimeException {

	/** the serial version uid. */
	private static final long serialVersionUID = -3081260136139364750L;

	/**
	 * Constructor for {@link RoomNotFoundException}.
	 */
	public RoomNotFoundException() {
		super("Der Raum konnte nicht gefunden werden!");
	}
}
