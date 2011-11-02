package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.exam.Exam;

public class HandleExamAction extends ActionSupport {
	private Exam exam;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String save() {
		return SUCCESS;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	
}
