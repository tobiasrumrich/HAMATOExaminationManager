package de.hatoma.exman.dao;

import java.util.Collection;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public interface IManipleDaoTTT extends IDaoTTT<Maniple> {

	Collection<Student> getStudents(long id);
}
