package de.hatoma.exman.model.study;

import java.io.Serializable;

import javax.persistence.Entity;
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

	public StudyBranch getBranchOfStudy() {
		return branchOfStudy;
	}

	public void setBranchOfStudy(StudyBranch branchOfStudy) {
		this.branchOfStudy = branchOfStudy;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
