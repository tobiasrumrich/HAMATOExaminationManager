package de.hatoma.exman.dao;

import java.util.Collection;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
/**
 * 
 * @author Marcel Schroeter, 3690
 *
 */
public interface IManipleDao extends IDao<Maniple> {

	Collection<Student> getStudentsForManiple(long id);
}
