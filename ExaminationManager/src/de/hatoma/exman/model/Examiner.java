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
@Table(name = "Examiners")
@AuditTable(value = "Examiners_Revisions")
public class Examiner implements Serializable {

	private static final long serialVersionUID = 1L;
	private String forename;
	private long id;
	private String lastname;

	public Examiner() {
	}

	public Examiner(String forename, String lastname) {
		super();
		this.forename = forename;
		this.lastname = lastname;
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

	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
