/**
 * @author Tobias Rumrich, 3638
 */
package de.hatoma.exman.action.helpers;

import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.Student;

public class ExamAttendanceBulkUpdateHelperBean {
	private String newGrade = "";
	private int numAttempt;
	private ExamAttendance previousExamAttendance;
	private Student student;

	public ExamAttendanceBulkUpdateHelperBean(Student student, int numAttempt,
			ExamAttendance previousExamAttendance) {
		super();
		this.previousExamAttendance = previousExamAttendance;
		this.student = student;
		this.numAttempt = numAttempt;

	}

	/**
	 * @return the newGrade
	 */
	public String getNewGrade() {
		return newGrade;
	}

	/**
	 * @return the numAttempt
	 */
	public int getNumAttempt() {
		return numAttempt + 1;
	}

	/**
	 * @return the previousExamAttendance
	 */
	public ExamAttendance getPreviousExamAttendance() {
		return previousExamAttendance;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param newGrade
	 *            the newGrade to set
	 */
	public void setNewGrade(String newGrade) {
		this.newGrade = newGrade;
	}

	/**
	 * @param numAttempt
	 *            the numAttempt to set
	 */
	public void setNumAttempt(int numAttempt) {
		this.numAttempt = numAttempt;
	}

	/**
	 * @param previousExamAttendance
	 *            the previousExamAttendance to set
	 */
	public void setPreviousExamAttendance(ExamAttendance previousExamAttendance) {
		this.previousExamAttendance = previousExamAttendance;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

}
