package de.hatoma.exman.dao;

import de.hatoma.exman.model.ExamAttendance;

public class ExamAttendanceDAO extends BaseDAO<ExamAttendance> implements IExamAttendanceDAO {

	public ExamAttendanceDAO() {
		super(ExamAttendance.class);
	}
	
}
