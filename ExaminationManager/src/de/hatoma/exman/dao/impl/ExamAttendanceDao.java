package de.hatoma.exman.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
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
		Criteria criteria = getCurrentSession().createCriteria(
				ExamAttendance.class).add(Restrictions.eq("exam", exam));
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
		
		
		Criteria criteria = getCurrentSession().createCriteria(ExamAttendance.class);
		criteria.add(Restrictions.eq("student", student));
		criteria.createCriteria("exam").add(Restrictions.eq("examSubject", examSubject));
		return criteria.list();
	}

	@Override
	public ExamAttendance findLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student) throws NoPreviousAttemptException {
		Criteria criteria = getCurrentSession().createCriteria(ExamAttendance.class);
		criteria.createCriteria("exam").add(Restrictions.eq("examSubject", examSubject)).addOrder( Order.desc("date"));
		criteria.add(Restrictions.eq("student", student));
		if (criteria.list().size() == 0) throw new NoPreviousAttemptException();
		return (ExamAttendance) criteria.list().get(criteria.list().size()-1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple,
			ExamGrade examGrade, ExamGrade oralExamGrade) {
		return getCurrentSession().createCriteria(ExamAttendance.class)
		.add(Restrictions.eq("examGrade", examGrade)).add(
				Restrictions.eq("supplementOralExamGrade", oralExamGrade)).createCriteria("student").add(Restrictions.eq("maniple", maniple)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple,
			ExamGrade examGrade) {
		return getCurrentSession().createCriteria(ExamAttendance.class)
		.add(Restrictions.eq("examGrade", examGrade)).add(
				Restrictions.isNull("supplementOralExamGrade")).createCriteria("student").add(Restrictions.eq("maniple", maniple)).list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ExamAttendance> findByStudentAndExamSubject(Student student,
			ExamSubject examSubject) {
		return getCurrentSession().createCriteria(ExamAttendance.class)
				.add(Restrictions.eq("student", student))
				.add(Restrictions.isNotNull("supplementOralExamGrade"))
				.createCriteria("exam")
				.add(Restrictions.eq("examSubject", examSubject)).list();
	}

}