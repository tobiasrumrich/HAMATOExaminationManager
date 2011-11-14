package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;

import de.hatoma.exman.model.ExamSubject;

@Component
public class ExamSubjectDAO extends BaseDAO<ExamSubject> implements IExamSubjectDAO {

	public ExamSubjectDAO() {
		super(ExamSubject.class);
	}

}
