package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.model.ExamSubject;

@Component
public class ExamSubjectDao extends BaseDao<ExamSubject> implements
		IExamSubjectDao {

	public ExamSubjectDao() {
		super(ExamSubject.class);
	}

}
