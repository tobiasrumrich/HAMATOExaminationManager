package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.test.BaseTest;

/**
 * @author Hannes Lemberg 3547
 * @author Marcel Schroeter, 3690
 */
public class ExamAttendanceDaoTest extends BaseTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultExamAttendance().getId();

		ExamAttendance e = getExamAttendanceDao().load(oldID);
		Assert.assertNotNull(e);
		Assert.assertNotNull(e.getId());
		Assert.assertTrue(e.getId() > 0);

		getExamAttendanceDao().delete(e);

		ExamAttendance e2 = getExamAttendanceDao().load(oldID);
		Assert.assertNull(e2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getExamAttendanceDao().findAll().size());

		final ExamAttendance e = new ExamAttendance();
		e.setAttempt(1);
		e.setExamGrade(ExamGrade.G10);
		e.setExam(getDefaultExam());
		e.setStudent(getDefaultStudent());
		getExamAttendanceDao().save(e);

		Assert.assertEquals(2, getExamAttendanceDao().findAll().size());

		getExamAttendanceDao().delete(e);

		Assert.assertEquals(1, getExamAttendanceDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<ExamAttendance> findAll = getExamAttendanceDao().findAll();
		ExamAttendance e = findAll.get(0);
		Assert.assertEquals(getDefaultExamAttendance(), e);
	}

	@Test
	public void testSave() {
		final ExamAttendance e = new ExamAttendance();
		e.setAttempt(1);
		e.setExamGrade(ExamGrade.G10);
		e.setExam(getDefaultExam());
		e.setStudent(getDefaultStudent());
		getExamAttendanceDao().save(e);

		// Persistieren erfolgriech
		Assert.assertNotNull(e.getId());
		Assert.assertTrue(e.getId() > 0);

		// Werte vergleichen
		ExamAttendance e2 = getExamAttendanceDao().load(e.getId());
		Assert.assertEquals(1, e2.getAttempt());
		Assert.assertEquals(ExamGrade.G10, e2.getExamGrade());
		Assert.assertEquals(getDefaultExam(), e2.getExam());
		Assert.assertEquals(getDefaultStudent(), e2.getStudent());
	}

	@Test
	public void testUpdate() {
		ExamAttendance e1 = getDefaultExamAttendance();

		e1.setAttempt(2);
		e1.setExamGrade(ExamGrade.G20);

		getExamAttendanceDao().update(e1);

		// Persistieren erfolgriech
		Assert.assertNotNull(e1.getId());
		Assert.assertTrue(e1.getId() > 0);

		// Werte vergleichen
		ExamAttendance e2 = getExamAttendanceDao().load(e1.getId());
		Assert.assertEquals(2, e2.getAttempt());
		Assert.assertEquals(ExamGrade.G20, e2.getExamGrade());
	}

	@Test
	public void testFindByExam() {

		// Die Default Exam Attendance muss schon vorhanden sein.
		Assert.assertTrue(getExamAttendanceDao().findByExam(getDefaultExam())
				.size() == 1);

		// Default Exam Attendance abspeichern
		ExamAttendance ea1 = new ExamAttendance();
		ea1.setExam(getDefaultExam());
		ea1.setAttempt(1);
		ea1.setExamGrade(ExamGrade.G40);
		ea1.setStudent(getDefaultStudent());
		getExamAttendanceDao().save(ea1);

		// Persistieren erfolgriech?
		Assert.assertNotNull(ea1.getId());
		Assert.assertTrue(ea1.getId() > 0);

		// Es muss ein Result mehr existieren
		Assert.assertEquals(2,
				getExamAttendanceDao().findByExam(getDefaultExam()).size());

		// Ein anderes Exam erzeugen und an ea2 anhängen
		ExamAttendance ea2 = getDefaultExamAttendance();
		Exam exam2 = getDefaultExam();
		exam2.setDate(getCurrentDate());
		ea2.setExam(exam2);
		// ea1 nochmal abspeichern (nicht updaten!)
		getExamAttendanceDao().save(ea2);

		// Persistieren erfolgriech?
		Assert.assertNotNull(ea2.getId());
		Assert.assertTrue(ea2.getId() > 0);

		// Liste muss bei 2 bleiben
		Assert.assertEquals(2,
				getExamAttendanceDao().findByExam(getDefaultExam()).size());

	}

	@Test
	public void testFindByManipleAndGrade() {

		// Die Default Exam Attendance muss schon vorhanden sein.
		Assert.assertEquals(
				1,
				getExamAttendanceDao().findbyManipleAndGrade(
						getDefaultManiple(), ExamGrade.G10).size());
		// Default Exam Attendance abspeichern
		Student s = new Student();
		s.setForename("asd");
		s.setLastname("asd");
		s.setManiple(getDefaultManiple());
		s.setMatriculationNumber("YXZ");
		getStudentDao().save(s);

		ExamAttendance ea1 = new ExamAttendance();
		ea1.setExam(getDefaultExam());
		ea1.setAttempt(1);
		ea1.setExamGrade(ExamGrade.G10);
		ea1.setStudent(s);
		getExamAttendanceDao().save(ea1);

		// Persistieren erfolgriech?
		Assert.assertNotNull(ea1.getId());
		Assert.assertTrue(ea1.getId() > 0);

		// Es muss ein Result mehr existieren
		Assert.assertEquals(
				2,
				getExamAttendanceDao().findbyManipleAndGrade(
						getDefaultManiple(), ExamGrade.G10).size());

		// Ein anderes Exam erzeugen und an ea2 anhängen
		ExamAttendance ea2 = getDefaultExamAttendance();
		ExamGrade examGrade2 = ExamGrade.G20;
		ea2.setExamGrade(examGrade2);

		Maniple m2 = getDefaultManiple();
		m2.setYear(2000);

		ea2.getStudent().setManiple(m2);
		// ea2 abspeichern
		getExamAttendanceDao().update(ea2);

		// Persistieren erfolgriech?
		Assert.assertNotNull(ea2.getId());
		Assert.assertTrue(ea2.getId() > 0);

		// Liste muss 1 sein
		Assert.assertEquals(
				1,
				getExamAttendanceDao().findbyManipleAndGrade(
						getDefaultManiple(), ExamGrade.G10).size());
	}

	@Test
	public void testFindLatestExamAttendanceOfStudentByExamSubject() {
		IExamAttendanceDao attendanceDao = getExamAttendanceDao();
		Student s = new Student();
		s.setForename("asd");
		s.setLastname("asd");
		s.setManiple(getDefaultManiple());
		s.setMatriculationNumber("YXZ");
		getStudentDao().save(s);

		ExamSubject examSubject = getDefaultExamSubject();
		Exam exam = getDefaultExam();

		Assert.assertEquals(0,
				attendanceDao.findByStudentAndExamSubject(s, examSubject)
						.size());
		Assert.assertTrue(examSubject.equals(exam.getExamSubject()));

		ExamAttendance attendance = new ExamAttendance();
		attendance.setAttempt(1);
		attendance.setExam(exam);
		attendance.setExamGrade(ExamGrade.G13);
		attendance.setStudent(s);
		attendanceDao.save(attendance);
		
		Assert.assertEquals(1,
				attendanceDao.findByStudentAndExamSubject(s, examSubject)
						.size());
	
	}

}
