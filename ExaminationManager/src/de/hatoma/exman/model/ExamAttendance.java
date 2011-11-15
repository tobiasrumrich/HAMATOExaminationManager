package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "ExamAttendances")
@AuditTable(value = "ADT_ExamAttendances")
public class ExamAttendance implements Serializable {

	private static final long serialVersionUID = 1L;
	private int attempt;
	private Exam exam;
	private ExamGrade examGrade;

	private long id;
	private Student student;

	private Date supplementalOralExamDate;
	private ExamGrade supplementOralExamGrade;

	public int getAttempt() {
		return attempt;
	}

	@ManyToOne(optional = false)
	public Exam getExam() {
		return exam;
	}

	public ExamGrade getExamGrade() {
		return examGrade;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@ManyToOne(optional = false)
	public Student getStudent() {
		return student;
	}

	public Date getSupplementalOralExamDate() {
		return supplementalOralExamDate;
	}

	public ExamGrade getSupplementOralExamGrade() {
		return supplementOralExamGrade;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setExamGrade(ExamGrade examGrade) {
		this.examGrade = examGrade;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStudent(Student param) {
		this.student = param;
	}

	public void setSupplementalOralExamDate(Date supplementalOralExamDate) {
		this.supplementalOralExamDate = supplementalOralExamDate;
	}

	public void setSupplementOralExamGrade(ExamGrade supplementOralExamGrade) {
		this.supplementOralExamGrade = supplementOralExamGrade;
	}

}
