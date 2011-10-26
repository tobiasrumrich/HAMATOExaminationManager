package de.hatoma.exman.model.exam;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

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
	private StudyBranch brachOfStudy;
	private String title;
	private int year;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StudyBranch getBrachOfStudy() {
		return brachOfStudy;
	}
	public void setBrachOfStudy(StudyBranch brachOfStudy) {
		this.brachOfStudy = brachOfStudy;
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
	
	
}
