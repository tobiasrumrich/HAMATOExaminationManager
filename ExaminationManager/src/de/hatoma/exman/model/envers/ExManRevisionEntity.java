package de.hatoma.exman.model.envers;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(ExManRevisionListener.class)
public class ExManRevisionEntity {
	
    @Id
    @GeneratedValue
    @RevisionNumber
    private int id;
	

	private static final long serialVersionUID = 1L;

	private String changedBy;


	/**
	 * @return the lastChangedBy
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * @param lastChangedBy the lastChangedBy to set
	 */
	public void setChangedBy(String lastChangedBy) {
		this.changedBy = lastChangedBy;
	}
	
	@RevisionTimestamp
    private long changedOn;
	/**
	 * @return the lastChangedOn
	 */
	public long getChangedOn() {
		return changedOn;
	}

	/**
	 * @param lastChangedOn the lastChangedOn to set
	 */
	public void setChangedOn(long lastChangedOn) {
		this.changedOn = lastChangedOn;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
