package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamType;

/**
 * @author Hannes Lemberg 3547
 */
public class ExamDaoTest extends BaseDaoTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultExam().getId();
		Exam s = getExamDao().load(oldID);
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		getExamDao().delete(s);

		Exam s2 = getExamDao().load(oldID);
		Assert.assertNull(s2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getExamDao().findAll().size());

		final Exam s = new Exam();
		s.setDate(getRandomDate());
		s.setExaminer(getDefaultExaminer());
		s.setExamType(ExamType.OralExam);
		s.setExamSubject(getDefaultExamSubject());
		getExamDao().save(s);

		Assert.assertEquals(2, getExamDao().findAll().size());

		getExamDao().delete(s);

		Assert.assertEquals(1, getExamDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<Exam> findAll = getExamDao().findAll();
		Exam s = findAll.get(0);
		Assert.assertEquals(getDefaultExam(), s);
	}

	@Test
	public void testSave() {
		final Exam s = new Exam();
		s.setDate(getRandomDate());
		s.setExaminer(getDefaultExaminer());
		s.setExamType(ExamType.OralExam);
		s.setExamSubject(getDefaultExamSubject());
		getExamDao().save(s);

		// Persistieren erfolgriech
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		// Werte vergleichen
		Exam s2 = getExamDao().load(s.getId());

	}
	//
	// @Test
	// public void testUpdate() {
	// Student s1 = getDefaultStudent();
	//
	// s1.setForename("A1");
	// s1.setLastname("A2");
	// s1.setMatriculationNumber("YYY");
	//
	// getExamDao().update(s1);
	//
	// // Persistieren erfolgriech
	// Assert.assertNotNull(s1.getId());
	// Assert.assertTrue(s1.getId() > 0);
	//
	// // Werte vergleichen
	// Student s2 = getExamDao().load(s1.getId());
	// Assert.assertEquals("A1", s2.getForename());
	// Assert.assertEquals("A2", s2.getLastname());
	// Assert.assertEquals(getDefaultManiple(), s2.getManiple());
	// Assert.assertEquals("YYY", s2.getMatriculationNumber());
	// }

}