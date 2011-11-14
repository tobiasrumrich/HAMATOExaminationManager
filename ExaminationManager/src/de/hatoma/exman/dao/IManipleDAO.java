package de.hatoma.exman.dao;

import java.util.Collection;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public interface IManipleDAO extends IDAO<Maniple> {

	Collection<Student> getStudents(long id);
}
