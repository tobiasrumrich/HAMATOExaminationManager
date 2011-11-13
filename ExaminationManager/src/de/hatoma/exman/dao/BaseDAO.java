package de.hatoma.exman.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDAO<T> extends HibernateDaoSupport implements IDAO<T> {
	private Class<T> clazz;

	public BaseDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Serializable save(T t) {
		return getHibernateTemplate().save(t);
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	
	/** {@inheritDoc} */
	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().loadAll(clazz.getClass());
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public T load(long id) {

		return (T) getHibernateTemplate().get(clazz.getClass(), id);
	}
}