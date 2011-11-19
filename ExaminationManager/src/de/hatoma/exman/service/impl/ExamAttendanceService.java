package de.hatoma.exman.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IManipleService;

@Component
public class ExamAttendanceService implements IExamAttendanceService {

	@Autowired
	private IExamAttendanceDao examAttendanceDao;
	@Autowired
	private IManipleDao manipleDao;

	@Autowired
	private IManipleService manipleService;

	@Override
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade) {

		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendance.setExamGrade(examGrade);
		// TODO Hier muss ermittelt werden, ob das wirklich der erste ist !!!
		examAttendance.setAttempt(1);
		examAttendanceDao.save(examAttendance);

		return examAttendance;
	}

	@Override
	public List<Student> getAllStudentsEligibleForExamAttendance(Exam exam) {
		Collection<Student> students = manipleService.getStudents(exam
				.getExamSubject().getManiple().getId());
		List<Student> eligibleStudents = new ArrayList<Student>();
		for (Student student : students) {
			ExamAttendance latestAttendance;
			try {
				latestAttendance = getLatestExamAttendanceOfStudentByExamSubject(
						exam.getExamSubject(), student);

			} catch (NoPreviousAttemptException e) {
				eligibleStudents.add(student);
				continue;
			}
			if ((latestAttendance.getExamGrade().equals(ExamGrade.G50) || latestAttendance
					.getExamGrade().equals(ExamGrade.G60))
					&& latestAttendance.getAttempt() < 3
					&& !latestAttendance.getExam().equals(exam)) {

				eligibleStudents.add(student);
			}
		}
		return eligibleStudents;
	}

	@Override
	public ExamAttendance getExamAttendanceById(long id) {
		return examAttendanceDao.load(id);
	}

	/**
	 * @return the examAttendanceDao
	 */
	public IExamAttendanceDao getExamAttendanceDao() {
		return examAttendanceDao;
	}

	@Override
	public List<ExamAttendance> getExamAttendancesByExamSubject(
			ExamSubject examSubject) {
		return examAttendanceDao.findByExamSubject(examSubject);
	}

	public List<ExamAttendance> getExamAttendancesForExam(Exam exam) {
		return examAttendanceDao.findByExam(exam);
	}

	@Override
	public List<ExamAttendance> getExamAttendancesForStudentByExamSubject(
			ExamSubject examSubject, Student student) {
		return examAttendanceDao.findByExamSubjectAndStudent(examSubject,
				student);
	}

	@Override
	public ExamAttendance getLatestExamAttendanceOfStudentByExamSubject(
			ExamSubject examSubject, Student student)
			throws NoPreviousAttemptException {
		ExamAttendance latestExamAttendance = examAttendanceDao
				.findLatestExamAttendanceOfStudentByExamSubject(examSubject,
						student);
		return latestExamAttendance;
	}

	public IManipleDao getManipleDao() {
		return manipleDao;
	}

	/**
	 * @return the manipleService
	 */
	public IManipleService getManipleService() {
		return manipleService;
	}

	@Override
	public List<ExamAttendance> getOralCandidates(long manipleId) {
		Maniple maniple = manipleDao.load(manipleId);
		List<ExamAttendance> allAttendances = examAttendanceDao
				.findbyManipleAndGrade(maniple, ExamGrade.G50);
		ExamSubject currentSubject;
		for (ExamAttendance attendance : allAttendances) {

			currentSubject = attendance.getExam().getExamSubject();
			// TODO Hannes, ich steh grad aufm Schlauch, ich wei� dass es gegen
			// den Nachbarschaftskodex (oder wie dat heisst^^) verstoesst,  aber ich
			// hab grad ne Blockade, wie ichs besser mach

			int numberOfOralExams;
			numberOfOralExams = examAttendanceDao.findByStudentAndExamSubject(
					attendance.getStudent(), currentSubject).size();
			if (numberOfOralExams >= 2) {
				allAttendances.remove(attendance);
			}

			// for every modul in modulliste
			// List list = hole gesamte historie where supplemental oral != 0
			// if list.size >= 2
			// l�sche modul from modulliste
			// endif
			// endfor
		}
		return allAttendances;
	}

	/**
	 * @param examAttendanceDao
	 *            the examAttendanceDao to set
	 */
	public void setExamAttendanceDao(IExamAttendanceDao examAttendanceDao) {
		this.examAttendanceDao = examAttendanceDao;
	}

	public void setManipleDao(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}

	/**
	 * @param manipleService
	 *            the manipleService to set
	 */
	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	@Override
	public void update(ExamAttendance examAttendance) throws Exception {
		examAttendanceDao.update(examAttendance);

	}

	@Override
	public void addOralExaminationResultToExamAttendance(
			ExamAttendance examAttendance, OralExamGrade oralExamGrade,
			Date oralExamDate) throws Exception {
		if (examAttendance.getSupplementOralExamGrade() != null) { throw new Exception(); }
		if (examAttendance.getExamGrade() != ExamGrade.G50) { throw new Exception(); }
		examAttendance.setSupplementOralExamGrade(oralExamGrade);
		examAttendance.setSupplementalOralExamDate(oralExamDate);
		examAttendanceDao.update(examAttendance);
		
		}

	@Override
	public List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> getAuditTrail(long examAttendanceId) {
		return examAttendanceDao.getAuditTrail(examAttendanceId);
	}
		
	}
