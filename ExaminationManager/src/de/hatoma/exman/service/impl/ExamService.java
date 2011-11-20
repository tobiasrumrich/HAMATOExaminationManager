package de.hatoma.exman.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import de.hatoma.exman.dao.IExamDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;

@Component
public class ExamService implements IExamService {

	@Autowired
	private IExamDao examDao;
	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExamSubjectService examSubjectService;

	@Override
	public Exam createExam(ExamType examType, ExamSubject examSubject,
			Date date, Examiner examiner) {
		Exam exam = new Exam();
		exam.setExamType(examType);
		exam.setExamSubject(examSubject);
		exam.setDate(date);
		exam.setExaminer(examiner);
		examDao.save(exam);
		return exam;
	}

	public void updateExam(Exam exam) throws Exception {
		examDao.update(exam);
	}

	@Override
	public List<Exam> getExamList() {
		return examDao.findAll();
	}

	@Override
	public Exam getExamById(long id) {
		return examDao.load(id);
	}

	/**
	 * @return the examDao
	 */
	public IExamDao getExamDao() {
		return examDao;
	}

	/**
	 * @param examDao
	 *            the examDao to set
	 */
	public void setExamDao(IExamDao examDao) {
		this.examDao = examDao;
	}

	@Override
	public Serializable save(Exam e) {
		return examDao.save(e);
	}

	@Override
	public void update(Exam exam) {
		examDao.update(exam);
	}

	@Override
	public Exam load(Long examId) {
		return examDao.load(examId);
	}

	@Override
	public Boolean isExamEditable(Exam exam) {
		List<ExamAttendance> examAttendancesForExam = getExamAttendanceService()
				.getExamAttendancesForExam(exam);

		return (examAttendancesForExam.size() == 0);
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hatoma.exman.service.IExamService#getAttendableExamsForStudent(de.
	 * hatoma.exman.model.Student, de.hatoma.exman.model.ExamSubject)
	 */
	@Override
	public Iterable<Exam> getAttendableExamsForStudent(Student student,
			ExamSubject subject) {
		if (examSubjectService.hasStudentPassedSubject(student, subject)) {
			return Lists.newArrayList();
		}

		List<Exam> examsForSubject = examDao.findAllForSubject(subject);

		Iterable<Exam> attendableExams = Iterables.filter(examsForSubject,
				new Predicate<Exam>() {

					@Override
					public boolean apply(final Exam exam) {
						return !examAttendanceService
								.hasStudentAttendedExam(exam);
					}
				});
		return attendableExams;
	}

	@Override
	public String getAttendableExamsForStudentJson(Student student,
			ExamSubject subject, final Function<String, String> getText) {
		// in eine Map umformen
		Iterable<Exam> attentableExamsForStudent = getAttendableExamsForStudent(
				student, subject);
		List<Map<String, String>> l = Lists.transform(
				Lists.newArrayList(attentableExamsForStudent),
				new Function<Exam, Map<String, String>>() {

					@Override
					public Map<String, String> apply(Exam e) {
						Map<String, String> m = new HashMap<String, String>();
						m.put("id", String.valueOf(e.getId()));
						StringBuilder val = new StringBuilder();
						val.append(e.getExaminer());
						val.append(", ");
						val.append(new SimpleDateFormat(getText
								.apply("examDateFormatNoTimeFormat")).format(e
								.getDate()));
						val.append(", ");
						val.append(getText.apply(e.getExamType().getKey()));
						m.put("stringValue", val.toString());

						return m;
					}
				});
		return new Gson().toJson(l);
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}
}
