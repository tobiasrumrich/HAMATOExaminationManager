package de.hatoma.exman.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.dao.StudentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@Transactional
public class StudentDaoTest {

	@Test
	public void test() {
		IStudentDao studentDao = new StudentDao();
		studentDao.load(0);

	}
}
