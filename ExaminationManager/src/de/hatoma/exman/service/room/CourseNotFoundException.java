/**
 * 
 */
package de.hatoma.exman.service.room;

/**
 * Exception that indicates a missing course.
 */
public class CourseNotFoundException extends RuntimeException {

	/** The serial version uid. */
	private static final long serialVersionUID = -8577997971202195020L;

	/**
	 * Constructor for {@link CourseNotFoundException}.
	 */
	public CourseNotFoundException() {
		super("Der Kurs konnte nicht gefunden werden!");
	}
}
