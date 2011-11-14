package de.hatoma.exman.dao.room;

import java.io.Serializable;
import java.util.List;

public interface IRoomBaseDAO<T> {

	void delete(T entity);

	List<T> findAll();

	T load(Serializable id);

	Serializable save(T entity);

	void update(T entity);

}