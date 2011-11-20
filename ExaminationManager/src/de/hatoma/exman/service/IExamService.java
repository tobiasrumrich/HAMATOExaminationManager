package de.hatoma.exman.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;

public interface IExamService {

	public Exam createExam(ExamType examType, ExamSubject examSubject,
			Date date, Examiner examiner);

	public Exam getExamById(long id);

	public List<Exam> getExamList();

	public Boolean isExamEditable(Exam exam);

	public Exam load(Long examId);

	public Serializable save(Exam e);

	public void update(Exam exam);
}
