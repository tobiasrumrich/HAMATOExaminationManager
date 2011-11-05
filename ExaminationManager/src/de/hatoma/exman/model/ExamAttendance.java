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
import de.hatoma.exman.model.Student;
import java.util.Collection;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;


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

	@OneToOne(optional = false)
	public Exam getExam() {
		return exam;
	}

	@OneToOne(optional = false)
	public ExamResult getExamResult() {
		return examResult;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
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

	@ManyToOne(optional = false)
	public Student getStudent() {
	    return student;
	}

	public void setStudent(Student param) {
	    this.student = param;
	}

	
}
