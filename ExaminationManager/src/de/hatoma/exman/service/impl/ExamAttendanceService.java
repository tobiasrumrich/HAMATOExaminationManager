package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDaoTTT;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;

@Component
public class ExamAttendanceService implements IExamAttendanceService {

	@Autowired
	private IExamAttendanceDaoTTT examAttendanceDaoTTT;
	
	
	@Override
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade) {
		
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendance.setExamGrade(examGrade);
		
		examAttendanceDaoTTT.save(examAttendance);
		
		return examAttendance;	
	}


	/**
	 * @return the examAttendanceDaoTTT
	 */
	public IExamAttendanceDaoTTT getExamAttendanceDAO() {
		return examAttendanceDaoTTT;
	}


	/**
	 * @param examAttendanceDaoTTT the examAttendanceDaoTTT to set
	 */
	public void setExamAttendanceDAO(IExamAttendanceDaoTTT examAttendanceDaoTTT) {
		this.examAttendanceDaoTTT = examAttendanceDaoTTT;
	}

}
