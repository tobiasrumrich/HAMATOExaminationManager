package de.hatoma.exman.dao.exceptions;
/**
 * 
 * @author Marcel Schroeter
 *
 */
public class StudentNotEligibleForOralExamException extends RuntimeException {


	private static final long serialVersionUID = -6846335544066288070L;

	public StudentNotEligibleForOralExamException() {
		super(
				"The student is not eligable for getting an oral examniation due to having a grade other than G50.");
	}
}
