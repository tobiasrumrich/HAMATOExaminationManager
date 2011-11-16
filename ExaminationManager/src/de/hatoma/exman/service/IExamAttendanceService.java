package de.hatoma.exman.service;

import java.util.List;

import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Student;

/**
 * Interface for an ExamAttendance Service
 * @author tobias
 *
 */
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
	 * @return List<ExamAttendace>
	 */
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam);

	/**
	 * Updates an examAttendance
	 * @param examAttendance
	 * @throws Exception
	 */
	public void update(ExamAttendance examAttendance) throws Exception;

	/**
	 * Delivers all students eligable for an oral exam
	 * @param manipleId
	 */	
	public List<ExamAttendance> getOralCandidates(long manipleId);

	/**
	 * Retrieves all ExamAttendances for a specified 
	 * @param examSubject
	 * @return List<ExamAttendance>
	 */
	public List<ExamAttendance> getExamAttendancesByExamSubject(ExamSubject examSubject);
	
	/**
	 * Retrieves all ExtamAttendances of a student for a specified subject, 
	 * @param examSubject
	 * @param student
	 * @return
	 */
	public List<ExamAttendance> getExamAttendancesForStudentByExamSubject (ExamSubject examSubject, Student student);
	
	/**
	 * Returns the most recent ExamAttendance of a Student for a specified ExamSubject
	 * @param examSubject
	 * @param student
	 * @return
	 */
	public ExamAttendance getLatestExamAttendanceOfStudentByExamSubject(ExamSubject examSubject, Student student) throws NoPreviousAttemptException;
}