package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Exams")
@AuditTable(value = "ADT_Exams")
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;

	private Examiner examiner;
	private ExamSubject examSubject;
	private ExamType examType;
	private long id;

	public Date getDate() {
		return date;
	}

	@ManyToOne(optional = false)
	public Examiner getExaminer() {
		return examiner;
	}

	/**
	 * @return the examSubject
	 */
	@OneToOne(optional = false)
	public ExamSubject getExamSubject() {
		return examSubject;
	}

	public ExamType getExamType() {
		return examType;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setExaminer(Examiner examiner) {
		this.examiner = examiner;
	}

	/**
	 * @param examSubject
	 *            the examSubject to set
	 */
	public void setExamSubject(ExamSubject examSubject) {
		this.examSubject = examSubject;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
