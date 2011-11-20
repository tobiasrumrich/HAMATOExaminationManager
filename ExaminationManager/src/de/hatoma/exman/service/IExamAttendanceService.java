package de.hatoma.exman.service;

import java.util.Date;
import java.util.List;

import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.model.Student;

/**
 * Interface for an ExamAttendance Service
 * 
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
	 * 
	 * @param exam
	 * @return List<ExamAttendace>
	 */
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam);

	/**
	 * Updates an examAttendance
	 * 
	 * @param examAttendance
	 * @throws Exception
	 */
	public void update(ExamAttendance examAttendance) throws Exception;

	/**
	 * Adds information about supplemental oral examination to existing
	 * attendance
	 * 
	 * @param examAttendance
	 * @param oralExamGrade
	 * @param oralExamDate
	 * @throws Exception
	 */
	public void addOralExaminationResultToExamAttendance(
			ExamAttendance examAttendance, OralExamGrade oralExamGrade,
			Date oralExamDate) throws Exception;

	/**
	 * Delivers all students eligable for an oral exam
	 * 
	 * @param manipleId
	 */
	public List<ExamAttendance> getOralCandidates(long manipleId);

	/**
	 * Retrieves all ExamAttendances for a specified
	 * 
	 * @param examSubject
	 * @return List<ExamAttendance>
	 */
	public List<ExamAttendance> getExamAttendancesByExamSubject(
			ExamSubject examSubject);

	/**
	 * Retrieves all ExtamAttendances of a student for a specified subject,
	 * 
	 * @param examSubject
	 * @param student
	 * @return
	 */
	public List<ExamAttendance> getExamAttendancesForStudentByExamSubject(
			ExamSubject examSubject, Student student);

	/**
	 * Returns the most recent ExamAttendance of a Student for a specified
	 * ExamSubject
	 * 
	 * @param examSubject
	 * @param student
	 * @return
	 */
	public ExamAttendance getLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student)
			throws NoPreviousAttemptException;

	/**
	 * Returns a list of all Students that eligible to attend to an exam
	 * 
	 * @param exam
	 * @return
	 */
	public List<Student> getAllStudentsEligibleForExamAttendance(Exam exam);

	/**
	 * Returns an ExamAttendance according to the given id.
	 * 
	 * @param id
	 * @return
	 */
	public ExamAttendance getExamAttendanceById(long id);

	/**
	 * Retrieves the audit trail for the ExamAttendace entity with the id
	 * provided
	 * 
	 * @param examAttendanceId
	 * @return
	 */
	public List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> getAuditTrail(
			long examAttendanceId);

	/**
	 * Retrieves a list of all current ExamAttendances for a student as JSON String
	 * 
	 * @param student
	 * @return
	 */
	String getAllCurrentExamAttendancesForStudentAsJSON(Student student);
}