package de.hatoma.exman.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.dao.exceptions.InvalidEntityIdException;
import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.dao.exceptions.OralGradeAlreadyExistantException;
import de.hatoma.exman.dao.exceptions.StudentNotEligibleForOralExamException;
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
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IManipleService;

@Component
public class ExamAttendanceService implements IExamAttendanceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100199136848800319L;
	@Autowired
	private IExamAttendanceDao examAttendanceDao;
	@Autowired
	private IExamSubjectService examSubjectService;


	@Autowired
	private IManipleDao manipleDao;

	@Autowired
	private IManipleService manipleService;

	@Autowired
	private IStudentDao studentDao;

	@Override
	public void addOralExaminationResultToExamAttendance(
			ExamAttendance examAttendance, OralExamGrade oralExamGrade,
			Date oralExamDate) throws Exception {
		if (examAttendance.getSupplementOralExamGrade() != null) {
			throw new OralGradeAlreadyExistantException();
		}
		if (examAttendance.getExamGrade() != ExamGrade.G50) {
			throw new StudentNotEligibleForOralExamException();
		}
		examAttendance.setSupplementOralExamGrade(oralExamGrade);
		examAttendance.setSupplementalOralExamDate(oralExamDate);
		examAttendanceDao.update(examAttendance);

	}

	@Override
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade) {

		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendance.setExamGrade(examGrade);
		try {
			ExamAttendance latestAttempt = examAttendanceDao
					.findLatestExamAttendanceOfStudentByExamSubject(
							exam.getExamSubject(), student);
			examAttendance.setAttempt(latestAttempt.getAttempt() + 1);
		} catch (NoPreviousAttemptException e) {
			examAttendance.setAttempt(1);
		}

		examAttendanceDao.save(examAttendance);

		return examAttendance;
	}

	@Override
	public String getAllCurrentExamAttendancesForManipleAsJSON(Maniple maniple,
			String idPattern, String dateFormat) {

		Collection<ExamSubject> examSubjects = examSubjectService
				.allSubjectsByManiple(maniple.getId());

		List<List<String>> s = new ArrayList<List<String>>();

		for (Student student : manipleService.getStudents(maniple.getId())) {

			for (ExamSubject examSubject : examSubjects) {
				ExamAttendance attendance;
				try {
					attendance = examAttendanceDao
							.findLatestExamAttendanceOfStudentByExamSubject(
									examSubject, student);
				} catch (NoPreviousAttemptException e) {
					continue;
				}
				List<String> m = new LinkedList<String>();
				m.add(student.getMatriculationNumber());
				m.add(student.getForename());
				m.add(student.getLastname());
				m.add(attendance.getExam().getExamSubject().toString());
				if (dateFormat != null) {
					m.add(new SimpleDateFormat(dateFormat).format(attendance
							.getExam().getDate()));
				} else {
					m.add(attendance.getExam().getDate().toString());
				}
				m.add(attendance.getExamGrade().getAsExpression());
				m.add(String.valueOf(attendance.getAttempt()));

				// Match the idPattern
				String idString;
				if (idPattern != null && idPattern.contains("_STUDID_")
						&& idPattern.contains("_SUBJID_")
						&& idPattern.contains("_ATTID_") && dateFormat != null) {

					idString = idPattern
							.replace("_STUDID_",
									String.valueOf(student.getId()))
							.replace(
									"_SUBJID_",
									String.valueOf(attendance.getExam()
											.getExamSubject().getId()))
							.replace("_ATTID_",
									String.valueOf(attendance.getId()));
				} else {
					idString = String.valueOf(attendance.getExam()
							.getExamSubject().getId());
				}
				m.add(idString);
				s.add(m);
			}
		}
		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		map.put("aaData", s);
		return new Gson().toJson(map);
	}

	@Override
	public String getAllCurrentExamAttendancesForStudentAsJSON(Student student) {

		Maniple maniple = manipleDao.load(student.getManiple().getId());

		Collection<ExamSubject> examSubjects = maniple.getExamSubjects();

		List<Map<String, String>> s = new ArrayList<Map<String, String>>();
		for (ExamSubject examSubject : examSubjects) {
			ExamAttendance attendance;
			try {
				attendance = examAttendanceDao
						.findLatestExamAttendanceOfStudentByExamSubject(
								examSubject, student);
			} catch (NoPreviousAttemptException e) {
				continue;
			}
			Map<String, String> m = new HashMap<String, String>();
			m.put("examSubject", attendance.getExam().getExamSubject()
					.toString());
			m.put("dateLastExam", attendance.getExam().getDate().toString());
			m.put("lastGrade", attendance.getExamGrade().getAsExpression());
			s.add(m);
		}

		return new Gson().toJson(s);
	}

	@Override
	public String getAllCurrentExamAttendancesForStudentAsJSON(Student student,
			String idPattern, String dateFormat) {
		Maniple maniple;
		try {
			maniple = manipleDao.load(student.getManiple().getId());
		} catch (Exception e) {
			return "{}";
		}

		Collection<ExamSubject> examSubjects = maniple.getExamSubjects();

		List<List<String>> s = new ArrayList<List<String>>();
		for (ExamSubject examSubject : examSubjects) {
			ExamAttendance attendance;
			try {
				attendance = examAttendanceDao
						.findLatestExamAttendanceOfStudentByExamSubject(
								examSubject, student);
			} catch (NoPreviousAttemptException e) {
				continue;
			}
			List<String> m = new LinkedList<String>();
			m.add(attendance.getExam().getExamSubject().toString());
			if (dateFormat != null) {
				m.add(new SimpleDateFormat(dateFormat).format(attendance
						.getExam().getDate()));
			} else {
				m.add(attendance.getExam().getDate().toString());
			}
			m.add(attendance.getExamGrade().getAsExpression());
			m.add(String.valueOf(attendance.getAttempt()));

			// Match the idPattern
			String idString;
			if (idPattern != null && idPattern.contains("_STUDID_")
					&& idPattern.contains("_SUBJID_")
					&& idPattern.contains("_ATTID_")) {

				idString = idPattern
						.replace("_STUDID_", String.valueOf(student.getId()))
						.replace(
								"_SUBJID_",
								String.valueOf(attendance.getExam()
										.getExamSubject().getId()))
						.replace("_ATTID_", String.valueOf(attendance.getId()));

			} else {
				idString = String.valueOf(attendance.getExam().getExamSubject()
						.getId());

			}
			m.add(idString);
			s.add(m);
		}

		Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
		map.put("aaData", s);
		return new Gson().toJson(map);
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
	public List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> getAuditTrail(
			long examAttendanceId) {
		return examAttendanceDao.getAuditTrail(examAttendanceId);
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

	@Override
	public List<ExamAttendance> getExamAttendancesForExam(Exam exam) {
		return examAttendanceDao.findByExam(exam);
	}

	@Override
	public List<ExamAttendance> getExamAttendancesForStudentByExamSubject(
			ExamSubject examSubject, Student student) {
		return examAttendanceDao.findByExamSubjectAndStudent(examSubject,
				student);
	}

	/**
	 * @return the examSubjectService
	 */
	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
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

			int numberOfOralExams;
			numberOfOralExams = examAttendanceDao.findByStudentAndExamSubject(
					attendance.getStudent(), currentSubject).size();
			if (numberOfOralExams >= 2) {
				allAttendances.remove(attendance);
			}
		}
		return allAttendances;
	}

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	@Override
	public boolean hasStudentAttendedExam(Exam exam) {
		List<ExamAttendance> attendances = getExamAttendancesForExam(exam);
		if (attendances == null) {
			return false;
		}

		return attendances.size() > 0;
	}

	@Override
	public Serializable save(ExamAttendance attendance) {
		return examAttendanceDao.save(attendance);
	}

	/**
	 * @param examAttendanceDao
	 *            the examAttendanceDao to set
	 */
	public void setExamAttendanceDao(IExamAttendanceDao examAttendanceDao) {
		this.examAttendanceDao = examAttendanceDao;
	}

	/**
	 * @param examSubjectService
	 *            the examSubjectService to set
	 */
	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
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

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public void update(ExamAttendance examAttendance) {
		examAttendanceDao.update(examAttendance);

	}

	@Override
	public void delete(long id) throws InvalidEntityIdException {
		ExamAttendance examAttendance = examAttendanceDao.load(id);
		if (examAttendance == null)
			throw new InvalidEntityIdException();
		examAttendanceDao.delete(examAttendance);

	}

	@Override
	public ExamGrade getCalculatedGrade(ExamAttendance examAttendance) {
		ExamGrade examGrade = examAttendance.getExamGrade();
		OralExamGrade oralExamGrade = examAttendance
				.getSupplementOralExamGrade();
		if (examGrade == null) {
			return null;
		}
		if (!examGrade.equals(ExamGrade.G50) || oralExamGrade == null) {
			return examGrade;
		}

		int iAVG = (examGrade.getAsNumber() + oralExamGrade.getAsNumber()) / 2; // z.B. 33
		
		ExamGrade avg = null;
		int lastDiff = Integer.MAX_VALUE;
		for(ExamGrade currentGrade : ExamGrade.values()) {
			int currentDiff = iAVG - currentGrade.getAsNumber();
			if(currentDiff >= 0 && currentDiff < lastDiff) {
				avg = currentGrade;
				lastDiff = currentDiff;
			}
		}

		return avg;
	}
}
