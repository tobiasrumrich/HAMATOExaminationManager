package de.hatoma.exman.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.ExamAttendance;

public class ExamAttendanceDAO extends HibernateDaoSupport implements IExamAttendanceDAO {

	@Override
	public ExamAttendance save(ExamAttendance entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}

	@Override
	public void delete(ExamAttendance entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public List<ExamAttendance> findAll() {
		return getHibernateTemplate().loadAll(ExamAttendance.class);
	}

	@Override
	public ExamAttendance load(long id) {
		return (ExamAttendance) getHibernateTemplate().get(ExamAttendance.class, id);
	}

}
