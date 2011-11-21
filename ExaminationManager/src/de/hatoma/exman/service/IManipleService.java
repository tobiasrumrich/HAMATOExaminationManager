package de.hatoma.exman.service;

import java.io.Serializable;
import java.util.Collection;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

public interface IManipleService extends Serializable {
	/**
	 * Legt das Manipel an
	 * 
	 * @param studyBranch
	 * @param year
	 * @return
	 */
	public Maniple createManiple(StudyBranch studyBranch, int year);

	/**
	 * @return alle vorhanden Manipel
	 */
	public Collection<Maniple> findAll();

	/**
	 * @return alle Manipel als Json
	 */
	public String getAllManiplesJson();

	/**
	 * Lädt Manipel mit ID
	 * 
	 * @param id
	 * @return
	 */
	public Maniple getById(long id);

	/**
	 * @return liefert alle vorhandenen Manipel
	 */
	public Collection<Maniple> getAll();

	/**
	 * @return anzahl der Manipel
	 */
	public long getManipleCount();

	/**
	 * @param id
	 * @return Studenten zum Manipel mit der ID
	 */
	public Collection<Student> getStudents(long id);

	public Maniple load(long id);
}
