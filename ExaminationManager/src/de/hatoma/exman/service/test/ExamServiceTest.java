package de.hatoma.exman.service.test;

import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.test.BaseTest;

public class ExamServiceTest extends BaseTest {
	private Exam exam1;
	private Exam exam2;
	private Student student;
	private ExamSubject subject;

	@Before
	public void before() {
		exam1 = getDefaultExam();
		student = getDefaultStudent();
		subject = exam1.getExamSubject();

		// zweites Exam im gleichen Fach anlegen
		exam2 = new Exam();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 27);
		exam2.setDate(calendar.getTime());
		exam2.setExaminer(getDefaultExaminer());
		exam2.setExamSubject(subject);
		exam2.setExamType(ExamType.WrittenExam);
		getExamDao().save(exam2);

		List<ExamAttendance> attendances = getExamAttendanceService()
				.getExamAttendancesForStudentByExamSubject(subject, student);
		Assert.assertEquals(1, attendances.size());
	}

	@Test
	public void testgetAttendableExamsForStudent1() {
		Iterable<Exam> notAttendedExamsForStudent = getExamService()
				.getAttendableExamsForStudent(student, subject);

		// Studi hats bereits bestanden, daher keine
		Assert.assertEquals(0, Iterables.size(notAttendedExamsForStudent));
	}

	@Test
	public void testgetAttendableExamsForStudent2() {
		ExamAttendance attendance = new ExamAttendance();
		attendance.setAttempt(1);
		attendance.setExam(exam2);
		attendance.setExamGrade(ExamGrade.G10);
		attendance.setStudent(student);
		getExamAttendanceService().save(attendance);

		List<ExamAttendance> attendances = getExamAttendanceService()
				.getExamAttendancesForStudentByExamSubject(subject, student);

		Assert.assertEquals(2, attendances.size());

		// Test
		Iterable<Exam> notAttendedExamsForStudent = getExamService()
				.getAttendableExamsForStudent(student, subject);

		Assert.assertEquals(0, Iterables.size(notAttendedExamsForStudent));
	}

	@Test
	public void testgetAttendableExamsForStudent3() {
		ExamSubject subjectWithoutExamAndAttendances = new ExamSubject();
		subjectWithoutExamAndAttendances.setDescription("Testdescr");
		subjectWithoutExamAndAttendances.setManiple(getDefaultManiple());
		subjectWithoutExamAndAttendances.setModuleIdentifier("ModIdentifier");
		subjectWithoutExamAndAttendances.setTitle("ModuleTitle");
		getExamSubjectDao().save(subjectWithoutExamAndAttendances);

		Iterable<Exam> notAttendedExamsForStudent = getExamService()
				.getAttendableExamsForStudent(student,
						subjectWithoutExamAndAttendances);

		Assert.assertEquals(0, Iterables.size(notAttendedExamsForStudent));
	}
}
