package de.hatoma.exman.dao.room;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.room.Course;

public class CourseDAO extends HibernateDaoSupport implements ICourseDAO {

	/** {@inheritDoc} */
	@Override
	public Course save(Course course) {
		return (Course) getHibernateTemplate().save(course);
	}

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

	/** {@inheritDoc} */
	@Override
	public Course load(long id) {
		return (Course) getHibernateTemplate().get(Course.class, id);
	}

	@Override
	public void update(Course entity) {
		// dummy um compilefehler zu verhindern
	}
}
