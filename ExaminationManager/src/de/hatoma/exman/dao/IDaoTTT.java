package de.hatoma.exman.dao;

import java.io.Serializable;
import java.util.List;

public interface IDaoTTT<T> {

	Serializable save(T entity);

	void delete(T entity);

	List<T> findAll();

	T load(Serializable id);

	void update(T entity);

}