package de.hatoma.exman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;

@Component
public class ExamAttendanceService implements IExamAttendanceService {

	@Autowired
	private IExamAttendanceDao examAttendanceDao;

	@Override
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade) {

		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendance.setExamGrade(examGrade);
		// TODO Hier muss ermittelt werden, ob das wirklich der erste ist !!!
		examAttendance.setAttempt(1);
		examAttendanceDao.save(examAttendance);

		return examAttendance;
	}
	
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam) {
		return examAttendanceDao.findByExam(exam);
	}

	/**
	 * @return the examAttendanceDao
	 */
	public IExamAttendanceDao getExamAttendanceDao() {
		return examAttendanceDao;
	}

	/**
	 * @param examAttendanceDao
	 *            the examAttendanceDao to set
	 */
	public void setExamAttendanceDao(IExamAttendanceDao examAttendanceDao) {
		this.examAttendanceDao = examAttendanceDao;
	}

	@Override
	public void update(ExamAttendance examAttendance) throws Exception {
		examAttendanceDao.update(examAttendance);
		
	}

	@Override
	public List<ExamAttendance> getExamAttendancesByExamSubject(
			ExamSubject examSubject) {
		return examAttendanceDao.findByExamSubject(examSubject);
	}

	@Override
	public List<ExamAttendance> getExamAttendancesForStudentByExamSubject(
			ExamSubject examSubject, Student student) {
		return examAttendanceDao.findByExamSubjectAndStudent(examSubject, student);
	}

	@Override
	public ExamAttendance getLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student) {
		return examAttendanceDao.findLatestExamAttendanceOfStudentByExamSubject(examSubject, student);
	}

}
