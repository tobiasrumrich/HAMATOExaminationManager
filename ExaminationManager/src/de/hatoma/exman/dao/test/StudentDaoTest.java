package de.hatoma.exman.dao.test;

import org.hamcrest.CoreMatchers;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.hatoma.exman.dao.IManipleDAO;
import de.hatoma.exman.dao.IStudentDAO;
import de.hatoma.exman.dao.IStudyBranchDAO;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

/**
 * @author Hannes Lemberg
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-config.xml" })
@Transactional
@TransactionConfiguration
public class StudentDaoTest {

	@Autowired
	private IStudentDAO studentDao;
	@Autowired
	private IManipleDAO manipleDAO;
	@Autowired
	private IStudyBranchDAO studyBranchDAO;

	@Before
	public void beforeMethod() {
		// Annahmen vor den Tests, es existiert nix für niemandend
		Assume.assumeThat(studyBranchDAO.findAll().size(),
				CoreMatchers.equalTo(0));
		Assume.assumeThat(manipleDAO.findAll().size(), CoreMatchers.equalTo(0));
		Assume.assumeThat(studentDao.findAll().size(), CoreMatchers.equalTo(0));

		// Vorbereitungen
		StudyBranch studyBranch = new StudyBranch();
		studyBranch.setBranchName("studyBranchBranchName");
		studyBranch.setLongTag("studyBranchLongTag");
		studyBranch.setShortTag("studyBranchShortTag");
		studyBranchDAO.save(studyBranch);

		Maniple maniple = new Maniple();
		maniple.setYear(2011);
		maniple.setStudyBranch(studyBranch);
		manipleDAO.save(maniple);

		Student student = new Student();
		student.setForename("studentForename");
		student.setLastname("studentLastname");
		student.setManiple(maniple);
		studentDao.save(student);

		// Annahmen nach den Vorbereitungen: es existiert eine Instanz für
		// jeweils alle
		Assume.assumeThat(studyBranchDAO.findAll().size(),
				CoreMatchers.equalTo(1));
		Assume.assumeThat(manipleDAO.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(studentDao.findAll().size(), CoreMatchers.equalTo(1));
	}

	@Test
	public void testSave() {

	}

	public IStudentDAO getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(IStudentDAO studentDao) {
		this.studentDao = studentDao;
	}

	public IManipleDAO getManipleDAO() {
		return manipleDAO;
	}

	public void setManipleDAO(IManipleDAO manipleDAO) {
		this.manipleDAO = manipleDAO;
	}

	public IStudyBranchDAO getStudyBranchDAO() {
		return studyBranchDAO;
	}

	public void setStudyBranchDAO(IStudyBranchDAO studyBranchDAO) {
		this.studyBranchDAO = studyBranchDAO;
	}

}
