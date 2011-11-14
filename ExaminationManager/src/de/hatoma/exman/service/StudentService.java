package de.hatoma.exman.service;

import de.hatoma.exman.dao.IStudentDAO;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public class StudentService implements IStudentService {

	private IStudentDAO studentDAO;
	@Override
	public Student createStudent(String forename, String lastname,
			Maniple maniple) {
		Student student = new Student();
		student.setForename(forename);
		student.setLastname(lastname);
		student.setManiple(maniple);
		studentDAO.save(student);
		return student;
	}
	/**
	 * @return the studentDAO
	 */
	public IStudentDAO getStudentDAO() {
		return studentDAO;
	}
	/**
	 * @param studentDAO the studentDAO to set
	 */
	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

}
