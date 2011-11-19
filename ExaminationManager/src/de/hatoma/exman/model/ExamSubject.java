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

@Entity
@Audited
@Table(name = "ExamSubjects")
@AuditTable(value = "ADT_ExamSubjects")
public class ExamSubject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private long id;
	private Maniple maniple;
	private String moduleIdentifier;
	private String title;

	public String getDescription() {
		return description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@ManyToOne
	public Maniple getManiple() {
		return maniple;
	}

	/**
	 * @return the moduleIdentifier
	 */
	// TODO: Es muss noch eine ID über ModulIdentifier und Manipel gebildet
	// werden!!
	public String getModuleIdentifier() {
		return moduleIdentifier;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setManiple(Maniple param) {
		this.maniple = param;
	}

	/**
	 * @param moduleIdentifier
	 *            the moduleIdentifier to set
	 */
	public void setModuleIdentifier(String moduleIdentifier) {
		this.moduleIdentifier = moduleIdentifier;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return moduleIdentifier + " - " + title;
	}

}
