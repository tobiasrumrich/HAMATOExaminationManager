package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.test.BaseTest;

/**
 * @author Hannes Lemberg
 * @author Marcel Schroeter, 3690
 */
public class StudyBranchDaoTest extends BaseTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultStudyBranch().getId();
		StudyBranch s = getStudyBranchDao().load(oldID);
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		getStudyBranchDao().delete(s);

		StudyBranch s2 = getStudyBranchDao().load(oldID);
		Assert.assertNull(s2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getStudyBranchDao().findAll().size());

		final StudyBranch s = new StudyBranch();
		s.setBranchName("Wirtschaftsinformatik");
		s.setLongTag("WInf");
		s.setShortTag("I");
		getStudyBranchDao().save(s);

		Assert.assertEquals(2, getStudyBranchDao().findAll().size());

		getStudyBranchDao().delete(s);

		Assert.assertEquals(1, getStudyBranchDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<StudyBranch> findAll = getStudyBranchDao().findAll();
		StudyBranch s = findAll.get(0);
		Assert.assertEquals(getDefaultStudyBranch(), s);
	}

	@Test
	public void testSave() {
		final StudyBranch s = new StudyBranch();
		s.setBranchName("Wirtschaftsinformatik");
		s.setLongTag("WInf");
		s.setShortTag("I");
		getStudyBranchDao().save(s);

		// Persistieren erfolgriech
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		// Werte vergleichen
		StudyBranch s2 = getStudyBranchDao().load(s.getId());
		Assert.assertEquals("Wirtschaftsinformatik", s2.getBranchName());
		Assert.assertEquals("WInf", s2.getLongTag());
		Assert.assertEquals("I", s2.getShortTag());
	}

	@Test
	public void testUpdate() {
		StudyBranch s1 = getDefaultStudyBranch();

		s1.setBranchName("Betriebswirtschaftslehre");
		s1.setLongTag("BWL");
		s1.setShortTag("B");

		getStudyBranchDao().update(s1);

		// Persistieren erfolgriech
		Assert.assertNotNull(s1.getId());
		Assert.assertTrue(s1.getId() > 0);

		// Werte vergleichen
		StudyBranch s2 = getStudyBranchDao().load(s1.getId());
		Assert.assertEquals("Betriebswirtschaftslehre", s2.getBranchName());
		Assert.assertEquals("BWL", s2.getLongTag());
		Assert.assertEquals("B", s2.getShortTag());
	}
}
