package de.hatoma.exman.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	private Collection<ExamAttendance> examAttendance;
	private String forename;
	private long id;

	private String lastname;

	private Maniple maniple;

	@OneToMany(mappedBy = "student")
	public Collection<ExamAttendance> getExamAttendance() {
	    return examAttendance;
	}

	public String getForename() {
		return forename;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	@ManyToOne(optional = false)
	public Maniple getManiple() {
		return maniple;
	}
	public void setExamAttendance(Collection<ExamAttendance> param) {
	    this.examAttendance = param;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setManiple(Maniple param) {
		this.maniple = param;
	}

	

}
