package de.hatoma.exman.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Basisinterface für alle Daos.
 * 
 * @author Hannes Lemberg 3547
 * 
 * @param <T>
 *            Zu bearbeitende Entität
 */
public interface IDao<T> extends Serializable {

	void delete(T entity);

	List<T> findAll();

	T load(Serializable id);

	Serializable save(T entity);

	void update(T entity);

}