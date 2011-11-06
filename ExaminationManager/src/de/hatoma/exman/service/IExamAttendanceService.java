package de.hatoma.exman.service;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.Student;

public interface IExamAttendanceService {

ExamAttendance createExamAttendanceForStudent(Student student, Exam exam, ExamGrade examGrade);

}
