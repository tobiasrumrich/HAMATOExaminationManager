package de.hatoma.exman.dao;

import de.hatoma.exman.model.Student;

public class StudentDAO extends BaseDAO<Student> implements IStudentDAO {

	public StudentDAO() {
		super(Student.class);
	}

}
