/**
 * 
 */
package de.hatoma.exman.dao;

import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;

/**
 * @author tobias
 * 
 */
public interface IExamAttendanceDao extends IDao<ExamAttendance> {
	public List<ExamAttendance> findByExam(Exam exam);
}
