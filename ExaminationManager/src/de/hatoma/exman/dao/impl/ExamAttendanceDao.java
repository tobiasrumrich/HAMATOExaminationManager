package de.hatoma.exman.dao.impl;

import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Student;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByExamSubject(ExamSubject examSubject) {
		Criteria criteria = getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("exam.examSubject", examSubject));
		return criteria.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByExamSubjectAndStudent(
			ExamSubject examSubject, Student student) {
		Criteria criteria = getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("exam.examSubject", examSubject));
		criteria.add(Restrictions.eq("student", student));
		return criteria.list();
	}

	@Override
	public ExamAttendance findLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student) {
		Criteria criteria = getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("exam.examSubject", examSubject));
		criteria.add(Restrictions.eq("student", student));
		criteria.addOrder( Order.desc("exam.date"));
		return (ExamAttendance) criteria.list().get(0);
	}

}
