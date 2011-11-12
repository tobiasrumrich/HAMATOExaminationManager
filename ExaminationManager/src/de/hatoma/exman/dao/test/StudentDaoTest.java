package de.hatoma.exman.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.hatoma.exman.dao.IStudentDAO;
import de.hatoma.exman.dao.StudentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@Transactional
public class StudentDaoTest {

	@Test
	public void test() {
		IStudentDAO studentDao = new StudentDAO();
		studentDao.load(0);

	}
}
