package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;
import de.hatoma.exman.model.Student;

@Component
public class StudentDAO extends BaseDAO<Student> implements IStudentDAO {
	public StudentDAO() {
		super(Student.class);
	}
}
