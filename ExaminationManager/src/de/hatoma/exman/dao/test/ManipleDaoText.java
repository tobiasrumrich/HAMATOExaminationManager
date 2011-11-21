package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.test.BaseTest;

/**
 * 
 * @author Hannes Lemberg
 * @author Marcel Schroeter, 3690
 *
 */
public class ManipleDaoText extends BaseTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultManiple().getId();
		Maniple m = getManipleDao().load(oldID);
		Assert.assertNotNull(m);
		Assert.assertNotNull(m.getId());
		Assert.assertTrue(m.getId() > 0);

		getManipleDao().delete(m);

		Maniple m2 = getManipleDao().load(oldID);
		Assert.assertNull(m2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getManipleDao().findAll().size());

		final Maniple m = new Maniple();
		m.setYear(2008);
		m.setStudyBranch(getDefaultStudyBranch());
		getManipleDao().save(m);

		Assert.assertEquals(2, getManipleDao().findAll().size());

		getManipleDao().delete(m);

		Assert.assertEquals(1, getManipleDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<Maniple> findAll = getManipleDao().findAll();
		Maniple m = findAll.get(0);
		Assert.assertEquals(getDefaultManiple(), m);
	}

	@Test
	public void testSave() {
		final Maniple m = new Maniple();
		m.setYear(2008);
		m.setStudyBranch(getDefaultStudyBranch());
		getManipleDao().save(m);

		// Persistieren erfolgriech
		Assert.assertNotNull(m.getId());
		Assert.assertTrue(m.getId() > 0);

		// Werte vergleichen
		Maniple m2 = getManipleDao().load(m.getId());
		Assert.assertEquals(2008, m2.getYear());
		Assert.assertEquals(getDefaultStudyBranch(), m2.getStudyBranch());
	}

	@Test
	public void testUpdate() {
		Maniple m1 = getDefaultManiple();

		m1.setYear(2008);
		m1.setStudyBranch(getDefaultStudyBranch());

		getManipleDao().update(m1);

		// Persistieren erfolgriech
		Assert.assertNotNull(m1.getId());
		Assert.assertTrue(m1.getId() > 0);

		// Werte vergleichen
		Maniple m2 = getManipleDao().load(m1.getId());
		Assert.assertEquals(2008, m2.getYear());
		Assert.assertEquals(getDefaultStudyBranch(), m2.getStudyBranch());
	}
}
