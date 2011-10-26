package de.hatoma.exman.model.exam.result;

import de.hatoma.exman.hibernateInternals.Auditable;
import de.hatoma.exman.hibernateInternals.EntityStatus;
import de.hatoma.exman.model.exam.Exam;
import de.hatoma.exman.model.person.Student;

public class ExamAttendance {
	private Exam exam;
	private Student student;
		
	private ExamResult examResult;

}
