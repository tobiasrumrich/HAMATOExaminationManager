package de.hatoma.exman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
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

		examAttendanceDao.save(examAttendance);

		return examAttendance;
	}
	
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam) {
		return examAttendanceDao.findByExam(exam);
	}

	/**
	 * @return the examAttendanceDao
	 */
	public IExamAttendanceDao getExamAttendanceDAO() {
		return examAttendanceDao;
	}

	/**
	 * @param examAttendanceDao
	 *            the examAttendanceDao to set
	 */
	public void setExamAttendanceDAO(IExamAttendanceDao examAttendanceDao) {
		this.examAttendanceDao = examAttendanceDao;
	}

	@Override
	public void update(ExamAttendance examAttendance) throws Exception {
		examAttendanceDao.update(examAttendance);
		
	}

}
