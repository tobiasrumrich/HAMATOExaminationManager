package de.hatoma.exman.dao.room;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.room.Lecture;

public class LectureDAO extends HibernateDaoSupport implements ILectureDAO {

	public Serializable save(Lecture lecture) {
		return getHibernateTemplate().save(lecture);
	}

	public void delete(Lecture lecture) {
		getHibernateTemplate().delete(lecture);
	}

	@SuppressWarnings("unchecked")
	public List<Lecture> findAll() {
		return getHibernateTemplate().loadAll(Lecture.class);
	}

	public Lecture load(long id) {
		return (Lecture) getHibernateTemplate().get(Lecture.class, id);
	}

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

	public void update(Lecture entity) {
		return; // nothing
	}
}
