package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Maniple")
@AuditTable(value = "ADT_Maniple")
public class Maniple implements Serializable {

	private static final long serialVersionUID = 1L;
	private StudyBranch branchOfStudy;
	private int year;
	private long id;
	private Collection<Student> student;


	@OneToOne(optional = false)
	public StudyBranch getBranchOfStudy() {
		return branchOfStudy;
	}

	public void setBranchOfStudy(StudyBranch branchOfStudy) {
		this.branchOfStudy = branchOfStudy;
	}

	@Basic(optional = false)
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "maniple")
	public Collection<Student> getStudent() {
	    return student;
	}

	public void setStudent(Collection<Student> param) {
	    this.student = param;
	}


}
