package de.hatoma.exman.service.room;

/**
 * Exception that indicates a duplicate room.
 */
public class CourseAlreadyExistsException extends RuntimeException {

	/** The serial version uid. */
	private static final long serialVersionUID = 7523284956831142205L;

	/**
	 * Constructor for {@link CourseAlreadyExistsException}.
	 */
	public CourseAlreadyExistsException() {
		super(
				"Für die Fachrichtung ist bereits ein Kurs mit der Nummer vorhanden!");
	}
}
