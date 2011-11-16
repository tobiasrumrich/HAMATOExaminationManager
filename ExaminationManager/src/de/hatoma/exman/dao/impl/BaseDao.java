package de.hatoma.exman.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.dao.IDao;

public abstract class BaseDao<T> implements IDao<T> {

	private Class<T> clazz;

	private SessionFactory sessionFactory;

	public BaseDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	/** {@inheritDoc} */
	@Override
	public void delete(T t) {
		getCurrentSession().delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public java.util.List<T> findAll() {
		return getCurrentSession().createCriteria(clazz).list();
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	};

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public T load(Serializable id) {

		return (T) getCurrentSession().get(clazz, id);
	};

	/** {@inheritDoc} */
	@Override
	public Serializable save(T t) {

		return getCurrentSession().save(t);
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void update(T entity) throws Exception{
		getCurrentSession().update(entity);
	}
}