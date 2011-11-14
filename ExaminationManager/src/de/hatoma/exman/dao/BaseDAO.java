package de.hatoma.exman.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDAO<T> implements IDAO<T> {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<T> clazz;

	public BaseDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.List<T> findAll() {
		return getCurrentSession().createCriteria(clazz).list();
	};

	@Override
	public void update(T entity) {
		getCurrentSession().update(entity);
	};

	/** {@inheritDoc} */
	@Override
	public Serializable save(T t) {

		return getCurrentSession().save(t);
	}

	/** {@inheritDoc} */
	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public T load(long id) {

		return (T) getCurrentSession().get(clazz.getClass(), id);
	}
}