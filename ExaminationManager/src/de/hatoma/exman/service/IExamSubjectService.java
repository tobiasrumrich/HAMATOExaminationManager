package de.hatoma.exman.service;

import java.util.Collection;
import java.util.Map;

import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public interface IExamSubjectService {

	Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple();

	Collection<ExamSubject> allSubjectsByManiple(long id);

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

	ExamSubject getExamSubject(long id);

	public long getExamSubjectCount();

	/**
	 * Überprüft, ob der Student bereits eine Prüfung für dieses Fach bestanden
	 * hat. TODO hal: mündliche prüfungen mit einbeziehen.
	 * 
	 * @param student
	 * @param subject
	 * @return
	 */
	boolean hasStudentPassedSubject(Student student, ExamSubject subject);

	public ExamSubject load(Long id);
}
