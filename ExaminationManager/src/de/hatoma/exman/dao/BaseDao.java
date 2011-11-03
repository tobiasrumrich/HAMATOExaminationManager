package de.hatoma.exman.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao<T> extends HibernateDaoSupport implements IDao<T> {
	private Class<T> clazz;

	public BaseDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public T save(T t) {
		return (T) getHibernateTemplate().save(t);
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
