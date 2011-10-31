package de.hatoma.exman.model.exam;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import de.hatoma.exman.model.study.StudyBranch;

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
	private int year;
	private String description;
	private StudyBranch studyBranch;
	private String moduleIdentifier;
	
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
	public StudyBranch getStudyBranch() {
	    return studyBranch;
	}
	public void setStudyBranch(StudyBranch param) {
	    this.studyBranch = param;
	}
	/**
	 * @return the moduleIdentifier
	 */
	@NaturalId
	public String getModuleIdentifier() {
		return moduleIdentifier;
	}
	/**
	 * @param moduleIdentifier the moduleIdentifier to set
	 */
	public void setModuleIdentifier(String moduleIdentifier) {
		this.moduleIdentifier = moduleIdentifier;
	}
	
	
}
