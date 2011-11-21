package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.test.BaseTest;

/**
 * Tests für den DAO ExamSubject
 * 
 * @author Hannes Lemberg 3547
 */
public class ExamSubjectDaoTest extends BaseTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultExamSubject().getId();
		ExamSubject s = getExamSubjectDao().load(oldID);
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		getExamSubjectDao().delete(s);

		ExamSubject s2 = getExamSubjectDao().load(oldID);
		Assert.assertNull(s2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getExamSubjectDao().findAll().size());

		final ExamSubject s = new ExamSubject();
		s.setDescription("Description");
		s.setManiple(getDefaultManiple());
		s.setModuleIdentifier("ModuleIdentifier");
		s.setTitle("titel");
		getExamSubjectDao().save(s);

		Assert.assertEquals(2, getExamSubjectDao().findAll().size());

		getExamSubjectDao().delete(s);

		Assert.assertEquals(1, getExamSubjectDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<ExamSubject> findAll = getExamSubjectDao().findAll();
		ExamSubject s = findAll.get(0);
		Assert.assertEquals(getDefaultExamSubject(), s);
	}

	@Test
	public void testSave() {
		final ExamSubject s = new ExamSubject();
		s.setDescription("Description");
		s.setManiple(getDefaultManiple());
		s.setModuleIdentifier("ModuleIdentifier");
		s.setTitle("titel");
		getExamSubjectDao().save(s);

		// Persistieren erfolgriech
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		// Werte vergleichen
		ExamSubject s2 = getExamSubjectDao().load(s.getId());
		Assert.assertEquals("Description", s2.getDescription());
		Assert.assertEquals(getDefaultManiple(), s2.getManiple());
		Assert.assertEquals("ModuleIdentifier", s2.getModuleIdentifier());
		Assert.assertEquals("titel", s2.getTitle());
	}

	@Test
	public void testFindByManiple() {
		IExamSubjectDao subjectDao = getExamSubjectDao();

		List<ExamSubject> byManiple = subjectDao
				.findByManiple(getDefaultManiple());
		Assert.assertEquals(1, byManiple.size());

		//
		ExamSubject examSubject = new ExamSubject();
		examSubject.setManiple(getDefaultManiple());
		examSubject.setDescription("aa");
		examSubject.setModuleIdentifier("aaa");
		examSubject.setTitle("aaa");
		subjectDao.save(examSubject);
		//
		List<ExamSubject> list2 = subjectDao.findByManiple(getDefaultManiple());
		Assert.assertEquals(2, list2.size());
	}
}
