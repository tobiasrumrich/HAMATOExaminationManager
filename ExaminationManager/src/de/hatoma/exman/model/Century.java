package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


@Entity
@Audited
@Table(name = "Centuries")
@AuditTable(value = "ADT_Centuries")
public class Century implements Serializable {
	private static final long serialVersionUID = 1L;
	private Character character;
	private long id;
	private StudyBranch studyBranch;
	private Maniple maniple;
	private Collection<Student> student;
	
	
	public Character getCharacter() {
		return character;
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	@OneToOne
	public StudyBranch getStudyBranch() {
		return studyBranch;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	public void setStudyBranch(StudyBranch studyBranch) {
		this.studyBranch = studyBranch;
	}
	@ManyToOne
	public Maniple getManiple() {
	    return maniple;
	}
	public void setManiple(Maniple param) {
	    this.maniple = param;
	}
	@OneToMany(mappedBy = "century")
	public Collection<Student> getStudent() {
	    return student;
	}
	public void setStudent(Collection<Student> param) {
	    this.student = param;
	}
	
}
