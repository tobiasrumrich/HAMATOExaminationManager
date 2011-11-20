package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
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

}
