package de.hatoma.exman.model.exam;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import de.hatoma.exman.model.person.Examiner;

@Entity
@Audited
@Table(name = "Exams")
@AuditTable(value = "ADT_Exams")
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private Examiner examiner;
	private Date date;
	private ExamSubject examSubject;

	public Examiner getExaminer() {
		return examiner;
	}

	public void setExaminer(Examiner examiner) {
		this.examiner = examiner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the examSubject
	 */
	public ExamSubject getExamSubject() {
		return examSubject;
	}

	/**
	 * @param examSubject the examSubject to set
	 */
	public void setExamSubject(ExamSubject examSubject) {
		this.examSubject = examSubject;
	}

}
