package de.hatoma.exman.dao.room;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.room.Course;

public class CourseDAO extends HibernateDaoSupport implements ICourseDAO {

	/** {@inheritDoc} */
	@Override
	public void delete(Course course) {
		getHibernateTemplate().delete(course);
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<Course> findAll() {
		return getHibernateTemplate().loadAll(Course.class);
	}

	@Override
	public Course load(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Course save(Course course) {
		return (Course) getHibernateTemplate().save(course);
	}

	@Override
	public void update(Course entity) {
		// dummy um compilefehler zu verhindern
	}
}
