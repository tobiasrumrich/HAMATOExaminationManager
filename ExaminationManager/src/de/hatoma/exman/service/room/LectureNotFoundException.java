package de.hatoma.exman.service.room;

/**
 * Exception that indicates a missing lecture.
 */
public class LectureNotFoundException extends RuntimeException {
	/** The serial version uid. */
	private static final long serialVersionUID = -513431878196129087L;

	/**
	 * Constructor for {@link LectureNotFoundException}.
	 */
	public LectureNotFoundException() {
		super("Die Veranstaltung konnte nicht gefunden werden.");
	}
}
