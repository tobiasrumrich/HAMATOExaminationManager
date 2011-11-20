package de.hatoma.exman.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;

@Component
public class ExamSubjectDao extends BaseDao<ExamSubject> implements
		IExamSubjectDao {

	public ExamSubjectDao() {
		super(ExamSubject.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamSubject> findByManiple(Maniple maniple) {
		return getCurrentSession().createCriteria(ExamSubject.class)
				.add(Restrictions.eq("maniple", maniple)).list();
	}
}
