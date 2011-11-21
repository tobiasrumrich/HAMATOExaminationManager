package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.test.BaseTest;

/**
 * @author Hannes Lemberg
 * @author Marcel Schroeter, 3690
 */
public class ExaminerDaoTest extends BaseTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultExaminer().getId();
		Examiner e = getExaminerDao().load(oldID);
		Assert.assertNotNull(e);
		Assert.assertNotNull(e.getId());
		Assert.assertTrue(e.getId() > 0);

		getExaminerDao().delete(e);

		Examiner s2 = getExaminerDao().load(oldID);
		Assert.assertNull(s2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getExaminerDao().findAll().size());

		final Examiner e = new Examiner();
		e.setForename("A");
		e.setLastname("B");
		getExaminerDao().save(e);

		Assert.assertEquals(2, getExaminerDao().findAll().size());

		getExaminerDao().delete(e);

		Assert.assertEquals(1, getExaminerDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<Examiner> findAll = getExaminerDao().findAll();
		Examiner e = findAll.get(0);
		Assert.assertEquals(getDefaultExaminer(), e);
	}

	@Test
	public void testSave() {
		final Examiner e = new Examiner();
		e.setForename("A");
		e.setLastname("B");
		getExaminerDao().save(e);

		// Persistieren erfolgriech
		Assert.assertNotNull(e.getId());
		Assert.assertTrue(e.getId() > 0);

		// Werte vergleichen
		Examiner e2 = getExaminerDao().load(e.getId());
		Assert.assertEquals("A", e2.getForename());
		Assert.assertEquals("B", e2.getLastname());
	}

	@Test
	public void testUpdate() {
		Examiner e1 = getDefaultExaminer();

		e1.setForename("A1");
		e1.setLastname("A2");

		getExaminerDao().update(e1);

		// Persistieren erfolgriech
		Assert.assertNotNull(e1.getId());
		Assert.assertTrue(e1.getId() > 0);

		// Werte vergleichen
		Examiner e2 = getExaminerDao().load(e1.getId());
		Assert.assertEquals("A1", e2.getForename());
		Assert.assertEquals("A2", e2.getLastname());
	}
}
