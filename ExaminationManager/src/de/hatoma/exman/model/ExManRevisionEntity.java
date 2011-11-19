package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(ExManRevisionListener.class)
public class ExManRevisionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String changedBy;

	@RevisionTimestamp
	private long changedOn;

	@Id
	@GeneratedValue
	@RevisionNumber
	private int id;

	/**
	 * @return the lastChangedBy
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * @return the lastChangedOn
	 */
	public long getChangedOn() {
		return changedOn;
	}
	
	public Date getChangedOnAsDate() {
		return new java.sql.Date(changedOn);  
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param lastChangedBy
	 *            the lastChangedBy to set
	 */
	public void setChangedBy(String lastChangedBy) {
		this.changedBy = lastChangedBy;
	}

	/**
	 * @param lastChangedOn
	 *            the lastChangedOn to set
	 */
	public void setChangedOn(long lastChangedOn) {
		this.changedOn = lastChangedOn;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
