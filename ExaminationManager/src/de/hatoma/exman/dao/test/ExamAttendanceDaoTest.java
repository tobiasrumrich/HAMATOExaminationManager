package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
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
	 Assert.assertEquals(1, getExamAttendanceDao().findByExam(getDefaultExam()).size());

	 // Default Exam Attendance abspeichern
	 ExamAttendance ea1 = getDefaultExamAttendance();
	 ea1.setExam(getDefaultExam());
	 getExamAttendanceDao().save(ea1);
	 
	 // Persistieren erfolgriech?
	 Assert.assertNotNull(ea1.getId());
	 Assert.assertTrue(ea1.getId() > 0);
	 
	 // Es muss ein Result mehr existieren
	 Assert.assertEquals(2, getExamAttendanceDao().findByExam(getDefaultExam()).size());
	 
	 // Ein anderes Exam erzeugen und an ea2 anhängen
	 ExamAttendance ea2 = getDefaultExamAttendance();
	 Exam exam2 = getDefaultExam();
	 exam2.setDate(getRandomDate());
	 ea2.setExam(exam2);
	 // ea1 nochmal abspeichern (nicht updaten!)
	 getExamAttendanceDao().save(ea2);

	 // Persistieren erfolgriech?
	 Assert.assertNotNull(ea2.getId());
	 Assert.assertTrue(ea2.getId() > 0);
	 
	 
	 // Liste muss bei 2 bleiben
	 Assert.assertEquals(2, getExamAttendanceDao().findByExam(getDefaultExam()).size());
	
	 }

	 @Test
	 public void testFindByExamSubject() {

	 // Die Default Exam Attendance muss schon vorhanden sein.
	 Assert.assertEquals(1, getExamAttendanceDao().findByExamSubject(getDefaultExamSubject()).size());

	 // Default Exam Attendance abspeichern
	 ExamAttendance ea1 = getDefaultExamAttendance();
	 ea1.getExam().setExamSubject(getDefaultExamSubject());
	 getExamAttendanceDao().save(ea1);
	 
	 // Persistieren erfolgriech?
	 Assert.assertNotNull(ea1.getId());
	 Assert.assertTrue(ea1.getId() > 0);
	 
	 // Es muss ein Result mehr existieren
	 Assert.assertEquals(2, getExamAttendanceDao().findByExamSubject(getDefaultExamSubject()).size());
	 
	 // Ein anderes Exam erzeugen und an ea2 anhängen
	 ExamAttendance ea2 = getDefaultExamAttendance();
	 ExamSubject examSubject2 = getDefaultExamSubject();
	 examSubject2.setDescription("andere beschreibung");
	 ea2.getExam().setExamSubject(examSubject2);
	 // ea1 nochmal abspeichern (nicht updaten!)
	 getExamAttendanceDao().save(ea2);

	 // Persistieren erfolgriech?
	 Assert.assertNotNull(ea2.getId());
	 Assert.assertTrue(ea2.getId() > 0);
	 
	 
	 // Liste muss bei 2 bleiben
	 Assert.assertEquals(2, getExamAttendanceDao().findByExamSubject(getDefaultExamSubject()).size());
	
	 }
	 
}
