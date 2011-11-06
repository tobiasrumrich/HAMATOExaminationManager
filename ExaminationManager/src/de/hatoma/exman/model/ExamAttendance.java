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
@Table(name = "ExamAttendances")
@AuditTable(value = "ADT_ExamAttendances")
public class ExamAttendance implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Exam exam;
	private long id;
	private Student student;
	
	private int attempt;
	private Date dateOfExam;
	private ExamGrade examGrade;
	
	private Date supplementalOralExamDate; 
	private ExamGrade supplementOralExamGrade;

	@OneToOne(optional = false)
	public Exam getExam() {
		return exam;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
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
