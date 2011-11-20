package de.hatoma.exman.dao.test;

import java.util.Calendar;
import java.util.Date;

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

import de.hatoma.exman.dao.IExamDao;
import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.dao.IExaminerDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.dao.IStudyBranchDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-config.xml" })
@Transactional
@TransactionConfiguration
public abstract class BaseDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private Exam defaultExam;
	private Examiner defaultExaminer;
	private ExamSubject defaultExamSubject;
	private Maniple defaultManiple;

	private Student defaultStudent;
	private StudyBranch defaultStudyBranch;
	@Autowired
	private IExamDao examDao;
	@Autowired
	private IExaminerDao examinerDao;
	@Autowired
	private IExamSubjectDao examSubjectDao;
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
		Assume.assumeThat(examDao.findAll().size(), CoreMatchers.equalTo(0));
		Assume.assumeThat(examinerDao.findAll().size(), CoreMatchers.equalTo(0));
		Assume.assumeThat(examSubjectDao.findAll().size(),
				CoreMatchers.equalTo(0));

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

		Examiner examiner = new Examiner();
		examiner.setForename("Emanuel");
		examiner.setLastname("Asdf");
		examinerDao.save(examiner);

		ExamSubject examSubject = new ExamSubject();
		examSubject.setDescription("Testdescr");
		examSubject.setManiple(maniple);
		examSubject.setModuleIdentifier("ModIdentifier");
		examSubject.setTitle("ModuleTitle");
		examSubjectDao.save(examSubject);

		Exam exam = new Exam();
		exam.setDate(Calendar.getInstance().getTime());
		exam.setExaminer(examiner);

		exam.setExamSubject(examSubject);
		exam.setExamType(ExamType.OralExam);
		examDao.save(exam);

		// Annahmen nach den Vorbereitungen: es existiert eine Instanz für
		// jeweils alle
		Assume.assumeThat(studyBranchDao.findAll().size(),
				CoreMatchers.equalTo(1));
		Assume.assumeThat(manipleDao.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(studentDao.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(examDao.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(examinerDao.findAll().size(), CoreMatchers.equalTo(1));
		Assume.assumeThat(examSubjectDao.findAll().size(),
				CoreMatchers.equalTo(1));

		// Alle lokal setzen
		this.defaultManiple = maniple;
		this.defaultStudent = student;
		this.defaultStudyBranch = studyBranch;
		this.defaultExam = exam;
		this.defaultExaminer = examiner;
		this.defaultExamSubject = examSubject;
	}

	public Exam getDefaultExam() {
		return defaultExam;
	}

	public Examiner getDefaultExaminer() {
		return defaultExaminer;
	}

	public ExamSubject getDefaultExamSubject() {
		return defaultExamSubject;
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

	public IExamDao getExamDao() {
		return examDao;
	}

	public IExaminerDao getExaminerDao() {
		return examinerDao;
	}

	public IExamSubjectDao getExamSubjectDao() {
		return examSubjectDao;
	}

	public IManipleDao getManipleDAO() {
		return manipleDao;
	}

	protected Date getRandomDate() {
		return Calendar.getInstance().getTime();
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

	public void setDefaultExaminer(Examiner defaultExaminer) {
		this.defaultExaminer = defaultExaminer;
	}

	public void setDefaultExamSubject(ExamSubject defaultExamSubject) {
		this.defaultExamSubject = defaultExamSubject;
	}

	public void setExamDao(IExamDao examDao) {
		this.examDao = examDao;
	}

	public void setExaminerDao(IExaminerDao examinerDao) {
		this.examinerDao = examinerDao;
	}

	public void setExamSubjectDao(IExamSubjectDao examSubjectDao) {
		this.examSubjectDao = examSubjectDao;
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
