package de.hatoma.exman.dao;

import java.util.List;

public interface IDao<T> {

	T save(T entity);

	void delete(T entity);

	List<T> findAll();

	T load(long id);

}
