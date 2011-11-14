package de.hatoma.exman.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {

	void delete(T entity);

	List<T> findAll();

	T load(Serializable id);

	Serializable save(T entity);

	void update(T entity);

}