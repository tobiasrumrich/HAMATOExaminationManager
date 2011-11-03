package de.hatoma.exman.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


@Entity
@Audited
@Table(name = "ExamAttendances")
@AuditTable(value = "ADT_ExamAttendances")
public class ExamAttendance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Exam exam;
	private ExamResult examResult;
	private long id;
	private Student student;

	@OneToOne
	public Exam getExam() {
		return exam;
	}

	@OneToOne
	public ExamResult getExamResult() {
		return examResult;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@OneToOne
	public Student getStudent() {
		return student;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
