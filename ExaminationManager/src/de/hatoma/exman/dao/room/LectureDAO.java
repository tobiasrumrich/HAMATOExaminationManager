package de.hatoma.exman.dao.room;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.room.Lecture;

public class LectureDAO extends HibernateDaoSupport implements ILectureDAO {
	/** {@inheritDoc} */
	@Override
	public Serializable save(Lecture lecture) {
		return getHibernateTemplate().save(lecture);
	}

	/** {@inheritDoc} */
	@Override
	public void delete(Lecture lecture) {
		getHibernateTemplate().delete(lecture);
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<Lecture> findAll() {
		return getHibernateTemplate().loadAll(Lecture.class);
	}

	/** {@inheritDoc} */
	@Override
	public Lecture load(long id) {
		return (Lecture) getHibernateTemplate().get(Lecture.class, id);
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<Lecture> findByRoom(long roomId) {
		Query query = getHibernateTemplate()
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Lecture as lecture join fetch lecture.room as room where room.id = :roomId");
		query.setLong("roomId", roomId);
		return query.list();
	}

	@Override
	public void update(Lecture entity) {
		return; // nothing
	}
}
