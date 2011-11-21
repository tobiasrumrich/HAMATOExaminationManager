package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Maniple")
@AuditTable(value = "Maniple_AuditTrail")
public class Maniple implements Serializable {

	private static final long serialVersionUID = 1L;
	private Collection<ExamSubject> examSubjects;
	private long id;
	private Collection<Student> students;
	private StudyBranch studyBranch;
	private int year;

	@OneToMany(mappedBy = "maniple")
	public Collection<ExamSubject> getExamSubjects() {
		return examSubjects;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@OneToMany()
	public Collection<Student> getStudents() {
		return students;
	}

	@ManyToOne(optional = false)
	public StudyBranch getStudyBranch() {
		return studyBranch;
	}

	@Basic(optional = false)
	public int getYear() {
		return year;
	}

	public void setExamSubjects(Collection<ExamSubject> param) {
		this.examSubjects = param;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	public void setStudyBranch(StudyBranch studyBranch) {
		this.studyBranch = studyBranch;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return studyBranch.getShortTag() + String.valueOf(year).substring(2);
	}
}
