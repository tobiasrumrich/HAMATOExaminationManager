package de.hatoma.exman.model.study;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Centuries")
@AuditTable(value = "ADT_Centuries")
public class Century implements Serializable {
	private static final long serialVersionUID = 1L;
	Maniple maniple;
	StudyBranch studyBranch;
	Character character;
}
