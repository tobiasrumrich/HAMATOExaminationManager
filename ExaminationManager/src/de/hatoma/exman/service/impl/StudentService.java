package de.hatoma.exman.service.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IStudentService;

@Component
public class StudentService implements IStudentService {

	@Autowired
	private IExamAttendanceService examAttendanceService;
	
	@Autowired
	private IStudentDao studentDao;

	private Gson gson;

	public StudentService() {
		gson = new Gson();
	}
	
	@Override
	public Student createStudent(String matriculationNumber, String forename, String lastname,
			Maniple maniple) {
		Student student = new Student();
		student.setMatriculationNumber(matriculationNumber);
		student.setForename(forename);
		student.setLastname(lastname);
		student.setManiple(maniple);
		studentDao.save(student);
		return student;
	}

	@Override
	public String getAllStudentsAsJson() {
		List<Student> allStudents = studentDao.findAll();
		List<Entry<String, String>> s = new ArrayList<Entry<String, String>>();

		for (Student currentStudent : allStudents) {
			s.add(new SimpleEntry<String, String>(String.valueOf(currentStudent.getId()), currentStudent
					.getForename() + " " + currentStudent.getLastname()));
		}

		return gson.toJson(s);
	}

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	@Override
	public Student getStudent(long id) {
		return studentDao.load(id);
	}

	@Override
	public long getStudentCount() {
		return studentDao.findAll().size();
	}

	/**
	 * @return the studentDao
	 */
	public IStudentDao getStudentDAO() {
		return studentDao;
	}

	/**
	 * @param examAttendanceService the examAttendanceService to set
	 */
	public void setExamAttendanceService(IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}
	
	/**
	 * @param studentDao
	 *            the studentDao to set
	 */
	public void setStudentDAO(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

}