/**
 * 
 */
package de.hatoma.exman.dao;

import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

/**
 * @author tobias
 * 
 */
public interface IExamAttendanceDao extends IDao<ExamAttendance> {
	public List<ExamAttendance> findByExam(Exam exam);

	public List<ExamAttendance> findByExamSubject(ExamSubject examSubject);
	public List<ExamAttendance> findByExamSubjectAndStudent(ExamSubject examSubject, Student student);

	public ExamAttendance findLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student);

	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple, ExamGrade examGrade,
			ExamGrade oralExamGrade);

	public List<ExamAttendance> findbyManipleAndGrade(Maniple maniple, ExamGrade examGrade);

	public List<ExamAttendance> findByStudentAndExamSubject(Student student,
			ExamSubject examSubject);

}