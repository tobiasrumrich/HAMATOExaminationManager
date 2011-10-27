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
@Table(name = "Centuries")
@AuditTable(value = "ADT_Centuries")
public class Century implements Serializable {
	private static final long serialVersionUID = 1L;
	private Maniple maniple;
	private StudyBranch studyBranch;
	private Character character;
	private long id;
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
