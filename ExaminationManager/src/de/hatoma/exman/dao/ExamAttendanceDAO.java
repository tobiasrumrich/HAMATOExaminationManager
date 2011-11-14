package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;

import de.hatoma.exman.model.ExamAttendance;

@Component
public class ExamAttendanceDAO extends BaseDAO<ExamAttendance> implements
		IExamAttendanceDAO {

	public ExamAttendanceDAO() {
		super(ExamAttendance.class);
	}
}
