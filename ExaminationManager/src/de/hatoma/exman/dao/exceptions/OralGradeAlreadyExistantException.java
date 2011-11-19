package de.hatoma.exman.dao.exceptions;

public class OralGradeAlreadyExistantException extends RuntimeException {
	/**
	 * author marcel
	 */
	private static final long serialVersionUID = -228100684265814068L;

	/** The serial version uid. */

	public OralGradeAlreadyExistantException() {
		super(
				"The oral grade could not be added to this ExamAttendance since there was already an Oral Grade given.");
	}
}
