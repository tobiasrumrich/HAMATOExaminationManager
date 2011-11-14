package de.hatoma.exman.dao.test;

import org.hamcrest.CoreMatchers;
import org.hibernate.SessionFactory;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.dao.IStudyBranchDao;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-config.xml" })
@Transactional
@TransactionConfiguration
public abstract class BaseDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private Maniple defaultManiple;
	private Student defaultStudent;
	private StudyBranch defaultStudyBranch;
	@Autowired
	private IManipleDao manipleDao;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IStudentDao studentDao;
	@Autowired
	private IStudyBranchDao studyBranchDao;

	@Before
	public void beforeMethod() {
		// Annahmen vor den Tests, es existiert nix für niemandend
		Assume.assumeThat(studyBranchDao.findAll().size(),
				CoreMatchers.equalTo(0));
		Assume.assumeThat(manipleDao.findAll().size(), CoreMatchers.equalTo(0));
		Assume.assumeThat(studentDao.findAll().size(), CoreMatchers.equalTo(0));

		// Vorbereitungen
		StudyBranch studyBranch = new StudyBranch();
		studyBranch.setBranchName("studyBranchBranchName");
		studyBranch.setLongTag("studyBranchLongTag");
		studyBranch.setShortTag("studyBranchShortTag");
		studyBranchDao.save(studyBranch);

		Maniple maniple = new Maniple();
		maniple.setYear(2011);
		maniple.setStudyBranch(studyBranch);
		manipleDao.save(maniple);

		Student student = new Student();
		student.setForename("studentForename");
		student.setLastname("studentLastname");
		student.setManiple(maniple);
		studentDao.save(student);

		// Annahmen nach den Vorbereitungen: es existiert eine Instanz für
		// jeweils alle
		Assume.assumeThat(studyBranchDao.findAll().size(),
				CoreMatchers.equalTo(1));
		Assume.assumeThat(manipleDao.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(studentDao.findAll().size(), CoreMatchers.equalTo(1));

		// Alle lokal setzen
		this.defaultManiple = maniple;
		this.defaultStudent = student;
		this.defaultStudyBranch = studyBranch;
	}

	public Maniple getDefaultManiple() {
		return defaultManiple;
	}

	public Student getDefaultStudent() {
		return defaultStudent;
	}

	public StudyBranch getDefaultStudyBranch() {
		return defaultStudyBranch;
	}

	public IManipleDao getManipleDAO() {
		return manipleDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	public IStudyBranchDao getStudyBranchDAO() {
		return studyBranchDao;
	}

	public void setManipleDAO(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setStudyBranchDAO(IStudyBranchDao studyBranchDao) {
		this.studyBranchDao = studyBranchDao;
	}

}
