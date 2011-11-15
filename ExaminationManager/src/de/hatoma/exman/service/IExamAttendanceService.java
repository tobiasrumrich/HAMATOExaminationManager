package de.hatoma.exman.service;

import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.Student;

public interface IExamAttendanceService {

	/**
	 * Creates a new ExamAttendance
	 * 
	 * @param student
	 * @param exam
	 * @param examGrade
	 * @return
	 */
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade);
	
	/**
	 * Retrieves all ExamAttendances for
	 * @param exam
	 * @return
	 */
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam);

}
