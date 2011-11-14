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

import de.hatoma.exman.dao.IManipleDaoTTT;
import de.hatoma.exman.dao.IStudentDaoTTT;
import de.hatoma.exman.dao.IStudyBranchDaoTTT;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-config.xml" })
@Transactional
@TransactionConfiguration
public abstract class BaseDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private IStudentDaoTTT studentDaoTTT;
	@Autowired
	private IManipleDaoTTT manipleDaoTTT;
	@Autowired
	private IStudyBranchDaoTTT studyBranchDaoTTT;
	@Autowired
	private SessionFactory sessionFactory;

	private Maniple defaultManiple;
	private StudyBranch defaultStudyBranch;
	private Student defaultStudent;

	@Before
	public void beforeMethod() {
		// Annahmen vor den Tests, es existiert nix für niemandend
		Assume.assumeThat(studyBranchDaoTTT.findAll().size(),
				CoreMatchers.equalTo(0));
		Assume.assumeThat(manipleDaoTTT.findAll().size(), CoreMatchers.equalTo(0));
		Assume.assumeThat(studentDaoTTT.findAll().size(), CoreMatchers.equalTo(0));

		// Vorbereitungen
		StudyBranch studyBranch = new StudyBranch();
		studyBranch.setBranchName("studyBranchBranchName");
		studyBranch.setLongTag("studyBranchLongTag");
		studyBranch.setShortTag("studyBranchShortTag");
		studyBranchDaoTTT.save(studyBranch);

		Maniple maniple = new Maniple();
		maniple.setYear(2011);
		maniple.setStudyBranch(studyBranch);
		manipleDaoTTT.save(maniple);

		Student student = new Student();
		student.setForename("studentForename");
		student.setLastname("studentLastname");
		student.setManiple(maniple);
		studentDaoTTT.save(student);

		// Annahmen nach den Vorbereitungen: es existiert eine Instanz für
		// jeweils alle
		Assume.assumeThat(studyBranchDaoTTT.findAll().size(),
				CoreMatchers.equalTo(1));
		Assume.assumeThat(manipleDaoTTT.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(studentDaoTTT.findAll().size(), CoreMatchers.equalTo(1));

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

	public IManipleDaoTTT getManipleDAO() {
		return manipleDaoTTT;
	}

	public IStudentDaoTTT getStudentDao() {
		return studentDaoTTT;
	}

	public IStudyBranchDaoTTT getStudyBranchDAO() {
		return studyBranchDaoTTT;
	}

	public void setManipleDAO(IManipleDaoTTT manipleDaoTTT) {
		this.manipleDaoTTT = manipleDaoTTT;
	}

	public void setStudentDao(IStudentDaoTTT studentDaoTTT) {
		this.studentDaoTTT = studentDaoTTT;
	}

	public void setStudyBranchDAO(IStudyBranchDaoTTT studyBranchDaoTTT) {
		this.studyBranchDaoTTT = studyBranchDaoTTT;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
