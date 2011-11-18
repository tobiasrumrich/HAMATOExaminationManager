package de.hatoma.exman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
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

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @param examAttendanceService the examAttendanceService to set
	 */
	public void setExamAttendanceService(IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	@Override
	public Student getValidOralStudent(long id) {
		//lade alle prüfungsergebnisse mit 5. und supplemental oral = 0
		//füge alle module in liste ein
		//for every modul in modulliste
		//	List list = hole gesamte historie where supplemental oral != 0
		//	if list.size <2
		//		module is ok
		//  endif
		//endfor
		//
//			Exam exam = examService.getExamById(id);
//			List<ExamAttendance> examAttendancesForExam = examAttendanceService.getExamAttendancesForExam(exam);
//			
//			return (examAttendancesForExam.size() == 0);

		return studentDao.load(id);
	}

	@Override
	public long getStudentCount() {
		return studentDao.findAll().size();
	}

}