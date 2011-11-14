package de.hatoma.exman.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.dao.IDaoTTT;

public abstract class BaseDao<T> implements IDaoTTT<T> {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<T> clazz;

	public BaseDao(Class<T> clazz) {
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
	public T load(Serializable id) {

		return (T) getCurrentSession().get(clazz, id);
	}
}