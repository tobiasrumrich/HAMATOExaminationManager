package de.hatoma.exman.dao;

import de.hatoma.exman.model.ExamSubject;

public class ExamSubjectDAO extends BaseDAO<ExamSubject> implements IExamSubjectDAO {

	public ExamSubjectDAO() {
		super(ExamSubject.class);
	}

}
