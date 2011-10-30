package de.hatoma.exman.model.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import de.hatoma.exman.model.study.Maniple;
import javax.persistence.ManyToOne;
import de.hatoma.exman.model.study.Century;


@Entity
@Audited
@Table(name = "Student")
@AuditTable(value = "ADT_Student")
public class Student extends AbstractPerson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;

	private Maniple maniple;

	private Century century;

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

	@ManyToOne
	public Maniple getManiple() {
	    return maniple;
	}

	public void setManiple(Maniple param) {
	    this.maniple = param;
	}

	@ManyToOne
	public Century getCentury() {
	    return century;
	}

	public void setCentury(Century param) {
	    this.century = param;
	}
	
	

}
