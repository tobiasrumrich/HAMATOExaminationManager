package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudentDaoTTT;
import de.hatoma.exman.model.Student;

@Component
public class StudentDao extends BaseDao<Student> implements IStudentDaoTTT {
	public StudentDao() {
		super(Student.class);
	}
}
