package de.hatoma.exman.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

/**
 * @author Hannes Lemberg 3547
 *
 */
public interface IExamSubjectService extends Serializable {

	/**
	 * 
	 * @return Liefert alle Prüfungsfächer geordnet nach Maniple zurück
	 */
	Map<Maniple, Collection<ExamSubject>> allSubjectsOrderdByManiple();

	/**
	 * Liefert alle Prüfungsfächer für das Manipel zurück
	 * 
	 * @param id
	 * @return
	 */
	Collection<ExamSubject> allSubjectsByManiple(long id);

	/**
	 * Erstellt ein neues Prüfungsfach
	 * 
	 * @param title
	 * @param description
	 * @param moduleIdentifier
	 * @param maniple
	 * @return
	 */
	ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple);

	/**
	 * Liefert die ExamSubjects für den Studenten zurück, die der Student noch
	 * nicht bestanden.
	 * 
	 * @param student
	 * @param subject
	 * @return
	 */
	String getAvailableExamSubjectsForStudentJson(Student student);

	/**
	 * Lädt das Prüfungsfach
	 * @param id
	 * @return
	 */
	ExamSubject getExamSubject(long id);

	/**
	 * @return die Gesamtzahl an vorhandenen Prüfungsfächern
	 */
	public long getExamSubjectCount();

	/**
	 * Überprüft, ob der Student bereits eine Prüfung für dieses Fach bestanden
	 * hat.
	 * 
	 * @param student
	 * @param subject
	 * @return
	 */
	boolean hasStudentPassedSubject(Student student, ExamSubject subject);

	public ExamSubject load(Long id);
}
