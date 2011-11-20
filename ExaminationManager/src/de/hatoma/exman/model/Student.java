package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Students")
@AuditTable(value = "Student_Revisions")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private Collection<ExamAttendance> examAttendance;
	private String forename;

	private long id;
	private String lastname;
	private Maniple maniple;
	private String matriculationNumber;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (forename == null) {
			if (other.forename != null)
				return false;
		} else if (!forename.equals(other.forename))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (matriculationNumber == null) {
			if (other.matriculationNumber != null)
				return false;
		} else if (!matriculationNumber.equals(other.matriculationNumber))
			return false;
		return true;
	}

	@OneToMany(mappedBy = "student")
	public Collection<ExamAttendance> getExamAttendance() {
		return examAttendance;
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

	@ManyToOne(optional = false)
	public Maniple getManiple() {
		return maniple;
	}

	/**
	 * @return the matriculationNumber
	 */
	public String getMatriculationNumber() {
		return matriculationNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((forename == null) ? 0 : forename.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime
				* result
				+ ((matriculationNumber == null) ? 0 : matriculationNumber
						.hashCode());
		return result;
	}

	public void setExamAttendance(Collection<ExamAttendance> param) {
		this.examAttendance = param;
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

	public void setManiple(Maniple param) {
		this.maniple = param;
	}

	/**
	 * @param matriculationNumber
	 *            the matriculationNumber to set
	 */
	public void setMatriculationNumber(String matriculationNumber) {
		this.matriculationNumber = matriculationNumber;
	}

	@Override
	public String toString() {
		return forename + " " + lastname + " [" + matriculationNumber + "]";
	}

}
