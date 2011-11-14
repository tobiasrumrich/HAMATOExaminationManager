package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDaoTTT;
import de.hatoma.exman.model.ExamAttendance;

@Component
public class ExamAttendanceDao extends BaseDao<ExamAttendance> implements
		IExamAttendanceDaoTTT {
	public ExamAttendanceDao() {
		super(ExamAttendance.class);
	}
}
