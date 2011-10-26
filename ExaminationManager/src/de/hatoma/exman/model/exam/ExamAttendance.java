package de.hatoma.exman.model.exam;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import de.hatoma.exman.model.person.Student;

@Entity
@Audited
@Table(name = "ExamAttendances")
@AuditTable(value = "ADT_ExamAttendances")
public class ExamAttendance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	
	//TODO: User Ÿber Spring Framework
	//private UserKlasse lastChangedBy;
	private Date lastChangedOn;
	
	
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ExamResult getExamResult() {
		return examResult;
	}

	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}

	private Exam exam;
	private Student student;

	private ExamResult examResult;
}
