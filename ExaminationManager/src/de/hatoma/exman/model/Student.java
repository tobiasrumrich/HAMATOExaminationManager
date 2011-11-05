package de.hatoma.exman.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import de.hatoma.exman.model.ExamAttendance;
import java.util.Collection;
import javax.persistence.OneToMany;


@Entity
@Audited
@Table(name = "Students")
@AuditTable(value = "Student_Revisions")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String forename;
	private String lastname;

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Maniple maniple;
	private Collection<ExamAttendance> examAttendance;
	@ManyToOne(optional = false)
	public Maniple getManiple() {
		return maniple;
	}

	public void setManiple(Maniple param) {
		this.maniple = param;
	}

	@OneToMany(mappedBy = "student")
	public Collection<ExamAttendance> getExamAttendance() {
	    return examAttendance;
	}

	public void setExamAttendance(Collection<ExamAttendance> param) {
	    this.examAttendance = param;
	}

	

}
