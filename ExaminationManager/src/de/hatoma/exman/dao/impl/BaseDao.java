package de.hatoma.exman.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.dao.IDao;

/**
 * Basisdao, der die grundlegenden CRUD-Operationen implemntiert.
 * 
 * @author Hannes Lemberg, 3547
 * 
 * @param <T> zum Dao gehörende Modelklasse
 */
public abstract class BaseDao<T> implements IDao<T> {

	private static final long serialVersionUID = 622916683037078982L;

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
	public void update(T entity) {
		getCurrentSession().update(entity);
	}
}