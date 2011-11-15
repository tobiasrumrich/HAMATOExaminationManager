package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IStudentService;

@Component
public class StudentService implements IStudentService {

	@Autowired
	private IStudentDao studentDao;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	@Override
	public Student createStudent(String forename, String lastname,
			Maniple maniple) {
		Student student = new Student();
		student.setForename(forename);
		student.setLastname(lastname);
		student.setManiple(maniple);
		studentDao.save(student);
		return student;
	}

	/**
	 * @return the studentDao
	 */
	public IStudentDao getStudentDAO() {
		return studentDao;
	}

	/**
	 * @param studentDao
	 *            the studentDao to set
	 */
	public void setStudentDAO(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Student getStudent(long id) {
		return studentDao.load(id);
	}

	@Override
	public Student getValidOralStudent(long id) {

		Student student = studentDao.load(id);

		return student;
	}

}
