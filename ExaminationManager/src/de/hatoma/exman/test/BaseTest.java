package de.hatoma.exman.test;

import java.util.Calendar;
import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.dao.IExamDao;
import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.dao.IExaminerDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.dao.IStudyBranchDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test-config.xml" })
@Transactional
@TransactionConfiguration
public abstract class BaseTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private Exam defaultExam;
	private Examiner defaultExaminer;
	private ExamSubject defaultExamSubject;
	private Maniple defaultManiple;
	private ExamAttendance defaultExamAttendance;
	private ExamGrade defaultExamGrade;

	private Student defaultStudent;
	private StudyBranch defaultStudyBranch;
	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExamDao examDao;
	@Autowired
	private IExaminerDao examinerDao;
	@Autowired
	private IExamAttendanceDao examAttendanceDao;
	@Autowired
	private IExamService examService;
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
		Assert.assertThat(studyBranchDao.findAll().size(),
				CoreMatchers.equalTo(0));
		Assert.assertThat(manipleDao.findAll().size(), CoreMatchers.equalTo(0));
		Assert.assertThat(studentDao.findAll().size(), CoreMatchers.equalTo(0));
		Assert.assertThat(examDao.findAll().size(), CoreMatchers.equalTo(0));
		Assert.assertThat(examinerDao.findAll().size(), CoreMatchers.equalTo(0));
		Assert.assertThat(examAttendanceDao.findAll().size(), CoreMatchers.equalTo(0));
		Assert.assertThat(examSubjectDao.findAll().size(),
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

		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setAttempt(1);
		examAttendance.setExamGrade(ExamGrade.G10);
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendanceDao.save(examAttendance);
		
		// Annahmen nach den Vorbereitungen: es existiert eine Instanz für
		// jeweils alle
		Assert.assertThat(studyBranchDao.findAll().size(),
				CoreMatchers.equalTo(1));
		Assert.assertThat(manipleDao.findAll().size(), CoreMatchers.equalTo(1));
		Assert.assertThat(studentDao.findAll().size(), CoreMatchers.equalTo(1));
		Assert.assertThat(examDao.findAll().size(), CoreMatchers.equalTo(1));
		Assert.assertThat(examinerDao.findAll().size(), CoreMatchers.equalTo(1));
		Assert.assertThat(examAttendanceDao.findAll().size(), CoreMatchers.equalTo(1));
		Assert.assertThat(examSubjectDao.findAll().size(),
				CoreMatchers.equalTo(1));

		// Alle lokal setzen
		this.defaultManiple = maniple;
		this.defaultStudent = student;
		this.defaultStudyBranch = studyBranch;
		this.defaultExam = exam;
		this.defaultExaminer = examiner;
		this.defaultExamSubject = examSubject;
		this.defaultExamAttendance = examAttendance;
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

	protected IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public IExamDao getExamDao() {
		return examDao;
	}

	public IExaminerDao getExaminerDao() {
		return examinerDao;
	}

	protected IExamService getExamService() {
		return examService;
	}

	public IExamSubjectDao getExamSubjectDao() {
		return examSubjectDao;
	}

	public IManipleDao getManipleDao() {
		return manipleDao;
	}

	protected Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	public IStudyBranchDao getStudyBranchDao() {
		return studyBranchDao;
	}

	public void setDefaultExaminer(Examiner defaultExaminer) {
		this.defaultExaminer = defaultExaminer;
	}

	public void setDefaultExamSubject(ExamSubject defaultExamSubject) {
		this.defaultExamSubject = defaultExamSubject;
	}

	public void setExamAttendanceService(IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	public void setExamDao(IExamDao examDao) {
		this.examDao = examDao;
	}

	public void setExaminerDao(IExaminerDao examinerDao) {
		this.examinerDao = examinerDao;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	public void setExamSubjectDao(IExamSubjectDao examSubjectDao) {
		this.examSubjectDao = examSubjectDao;
	}

	public void setManipleDao(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setStudyBranchDao(IStudyBranchDao studyBranchDao) {
		this.studyBranchDao = studyBranchDao;
	}

	public ExamAttendance getDefaultExamAttendance() {
		return defaultExamAttendance;
	}

	public void setDefaultExamAttendance(ExamAttendance defaultExamAttendance) {
		this.defaultExamAttendance = defaultExamAttendance;
	}

	public IExamAttendanceDao getExamAttendanceDao() {
		return examAttendanceDao;
	}

	public void setExamAttendanceDao(IExamAttendanceDao examAttendanceDao) {
		this.examAttendanceDao = examAttendanceDao;
	}

	public ExamGrade getDefaultExamGrade() {
		return defaultExamGrade;
	}

	public void setDefaultExamGrade(ExamGrade defaultExamGrade) {
		this.defaultExamGrade = defaultExamGrade;
	}

}
