package de.hatoma.exman.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamDao;
import de.hatoma.exman.dao.exceptions.EntityIsFrozenException;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;

@Component
public class ExamDao extends BaseDao<Exam> implements IExamDao {

	public ExamDao() {
		super(Exam.class);
	}

	@Override
	public void update(Exam exam) {

		Criteria criteria = getCurrentSession().createCriteria(
				ExamAttendance.class).add(Restrictions.eq("exam", exam));

		if (criteria.list().size() != 0) {
			throw new EntityIsFrozenException();
		}
		getCurrentSession().update(exam);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> findAllForSubject(ExamSubject subject) {
		return getCurrentSession().createCriteria(Exam.class)
				.add(Restrictions.eq("examSubject", subject)).list();
	}

}
