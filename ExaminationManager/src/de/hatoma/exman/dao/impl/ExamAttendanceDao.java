package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.model.ExamAttendance;

@Component
public class ExamAttendanceDao extends BaseDao<ExamAttendance> implements
		IExamAttendanceDao {
	public ExamAttendanceDao() {
		super(ExamAttendance.class);
	}
}
