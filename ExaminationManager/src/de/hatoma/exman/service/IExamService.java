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

public interface IExamService {

	public Exam createExam(ExamType examType, ExamSubject examSubject,
			Date date, Examiner examiner);

	public List<Exam> getExamList();

	public Exam getExamById(long id);

	public Serializable save(Exam e);

	public void update(Exam exam);

	public Exam load(Long examId);

	public Boolean isExamEditable(Exam exam);

	public String getAttendableExamsForStudentJson(Student student,
			ExamSubject subject, Function<String, String> getText);

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
}
