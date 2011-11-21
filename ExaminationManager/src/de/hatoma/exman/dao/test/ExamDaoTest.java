package de.hatoma.exman.dao.test;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.test.BaseTest;

/**
 * @author Hannes Lemberg 3547
 */
public class ExamDaoTest extends BaseTest {
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
		s.setDate(getCurrentDate());
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
		s.setDate(getCurrentDate());
		s.setExaminer(getDefaultExaminer());
		s.setExamType(ExamType.OralExam);
		s.setExamSubject(getDefaultExamSubject());
		getExamDao().save(s);

		// Persistieren erfolgriech
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		Exam s2 = getExamDao().load(s.getId());
		Assert.assertNotNull(s2);
	}

	@Test
	public void testUpdate() {
		final Exam s1 = new Exam();
		Date d1 = getCurrentDate();
		s1.setDate(d1);
		s1.setExaminer(getDefaultExaminer());
		s1.setExamType(ExamType.OralExam);
		s1.setExamSubject(getDefaultExamSubject());
		getExamDao().save(s1);

		Assert.assertTrue(s1.getId() > 0);

		s1.setDate(getCurrentDate());
		s1.setExaminer(getDefaultExaminer());
		s1.setExamType(ExamType.OralExam);
		s1.setExamSubject(getDefaultExamSubject());

		getExamDao().update(s1);

		// Persistieren erfolgriech
		Assert.assertNotNull(s1.getId());
		Assert.assertTrue(s1.getId() > 0);

		// Werte vergleichen
		Exam s2 = getExamDao().load(s1.getId());
		Assert.assertNotNull(s2);
	}

}
