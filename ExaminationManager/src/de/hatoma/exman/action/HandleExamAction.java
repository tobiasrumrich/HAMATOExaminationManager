package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.exam.Exam;

public class HandleExamAction extends ActionSupport {
	private Exam exam;
	private String lecturer;
	private String date;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String save() {
		return SUCCESS;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
