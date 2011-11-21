/**
 * 
 */
package de.hatoma.exman.dao;

import java.util.List;

import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

/**
 * @author Tobias Rumrich, 3638
 * 
 */
public interface IExamAttendanceDao extends IDao<ExamAttendance> {
	public List<ExamAttendance> findByExam(Exam exam);

	public List<ExamAttendance> findByExamSubjectAndStudent(
			ExamSubject examSubject, Student student);

	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple,
			ExamGrade examGrade);

	public List<ExamAttendance> findByStudentAndExamSubject(Student student,
			ExamSubject examSubject);

	public ExamAttendance findLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student)
			throws NoPreviousAttemptException;

	public List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> getAuditTrail(
			long id);

}