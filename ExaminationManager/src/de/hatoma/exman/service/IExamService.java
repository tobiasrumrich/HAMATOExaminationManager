package de.hatoma.exman.service;

import java.util.Date;
import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;

public interface IExamService {

	public Exam createExam(ExamSubject examSubject, Date date, Examiner examiner);

	public List<Exam> getExamList();

}
