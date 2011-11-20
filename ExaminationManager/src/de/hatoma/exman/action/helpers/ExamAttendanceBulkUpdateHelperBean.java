/**
 * @author Tobias Rumrich, 3638
 */
package de.hatoma.exman.action.helpers;

import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.Student;

public class ExamAttendanceBulkUpdateHelperBean {
	private ExamAttendance previousExamAttendance;
	private Student student;
	private int numAttempt;
	private String newGrade = "";
	
	public ExamAttendanceBulkUpdateHelperBean(
			Student student,
			int numAttempt, ExamAttendance previousExamAttendance) {
		super();
		this.previousExamAttendance = previousExamAttendance;
		this.student = student;
		this.numAttempt = numAttempt;

	}
	

	/**
	 * @return the previousExamAttendance
	 */
	public ExamAttendance getPreviousExamAttendance() {
		return previousExamAttendance;
	}

	/**
	 * @param previousExamAttendance the previousExamAttendance to set
	 */
	public void setPreviousExamAttendance(ExamAttendance previousExamAttendance) {
		this.previousExamAttendance = previousExamAttendance;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the numAttempt
	 */
	public int getNumAttempt() {
		return numAttempt+1;
	}

	/**
	 * @param numAttempt the numAttempt to set
	 */
	public void setNumAttempt(int numAttempt) {
		this.numAttempt = numAttempt;
	}

	/**
	 * @return the newGrade
	 */
	public String getNewGrade() {
		return newGrade;
	}

	/**
	 * @param newGrade the newGrade to set
	 */
	public void setNewGrade(String newGrade) {
		this.newGrade = newGrade;
	}
	
}
