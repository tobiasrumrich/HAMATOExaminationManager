package de.hatoma.exman.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.common.base.Function;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Student;

/**
 * @author Hannes Lemberg 3547
 * 
 */
public interface IExamService extends Serializable {

	public Exam createExam(ExamType examType, ExamSubject examSubject,
			Date date, Examiner examiner);

	/**
	 * Liefert die Prüfungen zurück, an denen der Student teilnehmen kann.
	 * 
	 * @param student
	 * @param subject
	 * @return ein Iterable mit den Prüfungen, falls Prüfungen vorliegen, ein
	 *         leeres Iterable
	 */
	Iterable<Exam> getAttendableExamsForStudent(Student student,
			ExamSubject subject);

	/**
	 * Liefert die Prüfungen zurück, an denen der Student teilnehmen kann, als
	 * JSON-String zurück.
	 * 
	 * @param student
	 * @param subject
	 * @param getText
	 * @return
	 */
	public String getAttendableExamsForStudentJson(Student student,
			ExamSubject subject, Function<String, String> getText);

	/**
	 * Gibt das Exam mit der angegeben ID zurück
	 * 
	 * @param id
	 * @return
	 */
	public Exam getExamById(long id);

	/**
	 * 
	 * @return alle vorhanden Exams zurück
	 */
	public List<Exam> getExamList();

	/**
	 * Prüft, ab das übergebene @param exam editiert werden darf.
	 * 
	 * @return
	 */
	public Boolean isExamEditable(Exam exam);

	/**
	 * Lädt das Exam mit der angegeben ID
	 * 
	 * @param examId
	 * @return
	 */
	public Exam load(Long examId);

	/**
	 * Spreichert das Objekt
	 * 
	 * @param e
	 * @return
	 */
	public Serializable save(Exam e);

	/**
	 * Führt ein update auf das Objekt durch.
	 * 
	 * @param exam
	 */
	public void update(Exam exam);
}
