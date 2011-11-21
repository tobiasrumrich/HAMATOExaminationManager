package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.model.Student;

@Component
public class StudentDao extends BaseDao<Student> implements IStudentDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5532241227949859311L;

	public StudentDao() {
		super(Student.class);
	}
}
