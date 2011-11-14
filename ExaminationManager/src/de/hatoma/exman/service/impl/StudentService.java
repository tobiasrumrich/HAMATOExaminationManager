package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudentDaoTTT;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IStudentService;

@Component
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDaoTTT studentDaoTTT;
	@Override
	public Student createStudent(String forename, String lastname,
			Maniple maniple) {
		Student student = new Student();
		student.setForename(forename);
		student.setLastname(lastname);
		student.setManiple(maniple);
		studentDaoTTT.save(student);
		return student;
	}
	/**
	 * @return the studentDaoTTT
	 */
	public IStudentDaoTTT getStudentDAO() {
		return studentDaoTTT;
	}
	/**
	 * @param studentDaoTTT the studentDaoTTT to set
	 */
	public void setStudentDAO(IStudentDaoTTT studentDaoTTT) {
		this.studentDaoTTT = studentDaoTTT;
	}

}
