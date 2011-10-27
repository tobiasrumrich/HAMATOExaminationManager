package de.hatoma.exman.model.study;

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
@AuditTable(value = "ADT_StudyBranches")
public class StudyBranch implements Serializable{

	private static final long serialVersionUID = 1L;
	private String shortTag;
	private String longTag;
	private String branchName;
	private long id;
	
	
	public String getShortTag() {
		return shortTag;
	}
	public void setShortTag(String shortTag) {
		this.shortTag = shortTag;
	}
	public String getLongTag() {
		return longTag;
	}
	public void setLongTag(String longTag) {
		this.longTag = longTag;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	
	
}
