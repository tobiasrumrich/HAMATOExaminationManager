package de.hatoma.exman.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
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
	 * Retrieves a list of all current ExamAttendances for a maniple as JSON
	 * String
	 * 
	 * @param maniple
	 *            the Maniple to query for
	 * @param idPattern
	 *            String with the pattern to use to encapsulate the
	 *            studyBranchId. _ID_ will be replaced with the actual long id
	 *            of the StudyBranch
	 * @return
	 */
	String getAllCurrentExamAttendancesForManipleAsJSON(Maniple maniple,
			String idPattern, String dateFormat);

	/**
	 * Retrieves a list of all current ExamAttendances for a student as JSON
	 * String
	 * 
	 * @param examAttendance
	 * @throws Exception
	 */
	String getAllCurrentExamAttendancesForStudentAsJSON(Student student);

	/**
	 * Retrieves a list of all current ExamAttendances for a student as JSON
	 * String
	 * 
	 * @param student
	 *            the Student to query for
	 * @param idPattern
	 *            String with the pattern to use to encapsulate the
	 *            studyBranchId. _ID_ will be replaced with the actual long id
	 *            of the StudyBranch
	 * @return
	 */
	String getAllCurrentExamAttendancesForStudentAsJSON(Student student,
			String idPattern);

	/**
	 * Returns a list of all Students that eligible to attend to an exam
	 * 
	 * @param exam
	 * @return
	 */
	public List<Student> getAllStudentsEligibleForExamAttendance(Exam exam);

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
	 * Returns an ExamAttendance according to the given id.
	 * 
	 * @param id
	 * @return
	 */
	public ExamAttendance getExamAttendanceById(long id);

	/**
	 * Retrieves all ExamAttendances for a specified
	 * 
	 * @param examSubject
	 * @return List<ExamAttendance>
	 */
	public List<ExamAttendance> getExamAttendancesByExamSubject(
			ExamSubject examSubject);

	/**
	 * Retrieves all ExamAttendances for
	 * 
	 * @param exam
	 * @return List<ExamAttendace>
	 */
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam);

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
	 * Delivers all students eligable for an oral exam
	 * 
	 * @param manipleId
	 */
	public List<ExamAttendance> getOralCandidates(long manipleId);

	/**
	 * Überprüft, ob der Student bereits an der Prüfung teilgenommen hat. Es
	 * wird jedoch nicht geprüft, ab er die Prüfung bestanden hat.
	 * 
	 * @param exam
	 * @return
	 */
	public boolean hasStudentAttendedExam(Exam exam);

	public Serializable save(ExamAttendance attendance);

	/**
	 * Updates an examAttendance
	 * 
	 * @param examAttendance
	 * @throws Exception
	 */
	public void update(ExamAttendance examAttendance) throws Exception;
}