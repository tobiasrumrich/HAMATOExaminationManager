package de.hatoma.exman.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;

@Component
public class ExamAttendanceDao extends BaseDao<ExamAttendance> implements
		IExamAttendanceDao {
	public ExamAttendanceDao() {
		super(ExamAttendance.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByExam(Exam exam) {
		Criteria criteria = getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("exam", exam));
		return criteria.list();
	}

}
