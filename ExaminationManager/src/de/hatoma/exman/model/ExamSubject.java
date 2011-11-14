package de.hatoma.exman.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import de.hatoma.exman.model.Maniple;
import javax.persistence.ManyToOne;


@Entity
@Audited
@Table(name = "ExamSubjects")
@AuditTable(value = "ADT_ExamSubjects")
public class ExamSubject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String description;
	private String moduleIdentifier;
	private Maniple maniple;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the moduleIdentifier
	 */
	//TODO: Es muss noch eine ID über ModulIdentifier und Manipel gebildet werden!!
	public String getModuleIdentifier() {
		return moduleIdentifier;
	}
	/**
	 * @param moduleIdentifier the moduleIdentifier to set
	 */
	public void setModuleIdentifier(String moduleIdentifier) {
		this.moduleIdentifier = moduleIdentifier;
	}
	@ManyToOne
	public Maniple getManiple() {
	    return maniple;
	}
	public void setManiple(Maniple param) {
	    this.maniple = param;
	}
	
	
}
