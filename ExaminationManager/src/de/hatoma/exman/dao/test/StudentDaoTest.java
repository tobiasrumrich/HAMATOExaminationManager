package de.hatoma.exman.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import de.hatoma.exman.model.Student;
import de.hatoma.exman.test.BaseTest;

/**
 * @author Hannes Lemberg
 */
public class StudentDaoTest extends BaseTest {
	@Test
	public void testDelete() {
		long oldID = getDefaultStudent().getId();
		Student s = getStudentDao().load(oldID);
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		getStudentDao().delete(s);

		Student s2 = getStudentDao().load(oldID);
		Assert.assertNull(s2);
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(1, getStudentDao().findAll().size());

		final Student s = new Student();
		s.setForename("A");
		s.setLastname("B");
		s.setManiple(getDefaultManiple());
		s.setMatriculationNumber("XXX");
		getStudentDao().save(s);

		Assert.assertEquals(2, getStudentDao().findAll().size());

		getStudentDao().delete(s);

		Assert.assertEquals(1, getStudentDao().findAll().size());
	}

	@Test
	public void testInitial() {
		List<Student> findAll = getStudentDao().findAll();
		Student s = findAll.get(0);
		Assert.assertEquals(getDefaultStudent(), s);
	}

	@Test
	public void testSave() {
		final Student s = new Student();
		s.setForename("A");
		s.setLastname("B");
		s.setManiple(getDefaultManiple());
		s.setMatriculationNumber("XXX");
		getStudentDao().save(s);

		// Persistieren erfolgriech
		Assert.assertNotNull(s.getId());
		Assert.assertTrue(s.getId() > 0);

		// Werte vergleichen
		Student s2 = getStudentDao().load(s.getId());
		Assert.assertEquals("A", s2.getForename());
		Assert.assertEquals("B", s2.getLastname());
		Assert.assertEquals(getDefaultManiple(), s2.getManiple());
		Assert.assertEquals("XXX", s2.getMatriculationNumber());
	}

	@Test
	public void testUpdate() {
		Student s1 = getDefaultStudent();

		s1.setForename("A1");
		s1.setLastname("A2");
		s1.setMatriculationNumber("YYY");

		getStudentDao().update(s1);

		// Persistieren erfolgriech
		Assert.assertNotNull(s1.getId());
		Assert.assertTrue(s1.getId() > 0);

		// Werte vergleichen
		Student s2 = getStudentDao().load(s1.getId());
		Assert.assertEquals("A1", s2.getForename());
		Assert.assertEquals("A2", s2.getLastname());
		Assert.assertEquals(getDefaultManiple(), s2.getManiple());
		Assert.assertEquals("YYY", s2.getMatriculationNumber());
	}
}
