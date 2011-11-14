package de.hatoma.exman.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "StudyBranches")
@AuditTable(value = "StudyBranches_Revisions")
public class StudyBranch implements Serializable {

	private static final long serialVersionUID = 1L;
	private String branchName;
	private long id;
	private String longTag;
	private String shortTag;

	public String getBranchName() {
		return branchName;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public String getLongTag() {
		return longTag;
	}

	public String getShortTag() {
		return shortTag;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public void setLongTag(String longTag) {
		this.longTag = longTag;
	}

	public void setShortTag(String shortTag) {
		this.shortTag = shortTag;
	}

}
