package de.hatoma.exman.dao;

import de.hatoma.exman.model.Student;

public class StudentDao extends BaseDao<Student> implements IStudentDao {

	public StudentDao() {
		super(Student.class);
	}

}
