package de.hatoma.exman.service;

import de.hatoma.exman.dao.IExamAttendanceDAO;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.Student;

public class ExamAttendanceService implements IExamAttendanceService {

	private IExamAttendanceDAO examAttendanceDAO;
	
	
	@Override
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade) {
		
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendance.setExamGrade(examGrade);
		
		examAttendanceDAO.save(examAttendance);
		
		return examAttendance;	
	}


	/**
	 * @return the examAttendanceDAO
	 */
	public IExamAttendanceDAO getExamAttendanceDAO() {
		return examAttendanceDAO;
	}


	/**
	 * @param examAttendanceDAO the examAttendanceDAO to set
	 */
	public void setExamAttendanceDAO(IExamAttendanceDAO examAttendanceDAO) {
		this.examAttendanceDAO = examAttendanceDAO;
	}

}
