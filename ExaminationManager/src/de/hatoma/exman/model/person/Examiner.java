package de.hatoma.exman.model.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


@Entity
@Audited
@Table(name = "Examiner")
@AuditTable(value = "ADT_Examiner")
public class Examiner extends AbstractPerson implements Serializable {

	private static final long serialVersionUID = 1L;
	

}
