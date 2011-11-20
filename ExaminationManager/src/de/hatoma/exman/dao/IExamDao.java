package de.hatoma.exman.dao;

import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;

public interface IExamDao extends IDao<Exam> {
	List<Exam> findAllForSubject(ExamSubject subject);
}
