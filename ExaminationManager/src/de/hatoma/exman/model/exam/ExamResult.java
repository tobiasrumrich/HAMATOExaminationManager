package de.hatoma.exman.model.exam;

import java.io.Serializable;
import java.util.Date;

public class ExamResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	//TODO: User Ÿber Spring Framework
	//private UserKlasse lastChangedBy;
	private Date lastChangedOn;
	
	private int attempt;
	private Date dateOfExam;
	private ExamGrade examGrade;
	
	private Date supplementalOralExamDate; 
	private ExamGrade supplementOralExamGrade;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getLastChangedOn() {
		return lastChangedOn;
	}
	public void setLastChangedOn(Date lastChangedOn) {
		this.lastChangedOn = lastChangedOn;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	public Date getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}
	public ExamGrade getExamGrade() {
		return examGrade;
	}
	public void setExamGrade(ExamGrade examGrade) {
		this.examGrade = examGrade;
	}
	public Date getSupplementalOralExamDate() {
		return supplementalOralExamDate;
	}
	public void setSupplementalOralExamDate(Date supplementalOralExamDate) {
		this.supplementalOralExamDate = supplementalOralExamDate;
	}
	public ExamGrade getSupplementOralExamGrade() {
		return supplementOralExamGrade;
	}
	public void setSupplementOralExamGrade(ExamGrade supplementOralExamGrade) {
		this.supplementOralExamGrade = supplementOralExamGrade;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
