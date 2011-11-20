package de.hatoma.exman.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;

@Component
public class ExamSubjectService implements IExamSubjectService {

	@Autowired
	private IExamSubjectDao examSubjectDao;

	@Autowired
	private IManipleDao manipleDao;

	@Autowired
	private IExamService examService;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	@Override
	public ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple) {
		ExamSubject examSubject = new ExamSubject();
		examSubject.setTitle(title);
		examSubject.setDescription(description);
		examSubject.setModuleIdentifier(moduleIdentifier);
		examSubject.setManiple(maniple);
		examSubjectDao.save(examSubject);
		return examSubject;
	}

	@Override
	public String getAvailableExamSubjectsForStudentJson(final Student student) {
		Iterable<Map<String, String>> propertyMap;
		if (student == null) {
			propertyMap = new ArrayList<Map<String, String>>();
		} else {
			Maniple maniple = student.getManiple();
			// alle laden
			List<ExamSubject> examSubjects = examSubjectDao
					.findByManiple(maniple);
			// nur solche herausfilter, für die tatsächlich prüfungen vorhanden
			// sind & die prüfung noch nicht bestanden wurde.
			Iterable<ExamSubject> notPassedSubjects = Iterables.filter(
					examSubjects, new Predicate<ExamSubject>() {
						@Override
						public boolean apply(ExamSubject subject) {
							// TODO: hal FALSCH!!
							return !hasStudentPassedSubject(student, subject);
						}
					});

			propertyMap = Iterables.transform(notPassedSubjects,
					new Function<ExamSubject, Map<String, String>>() {
						@Override
						public Map<String, String> apply(ExamSubject e) {
							HashMap<String, String> manipleData = new HashMap<String, String>();
							manipleData.put("description", e.getDescription());
							manipleData.put("moduleIdentifier",
									e.getModuleIdentifier());
							manipleData.put("id", String.valueOf(e.getId()));
							manipleData.put("manipleId",
									String.valueOf(e.getManiple().getId()));
							manipleData.put("stringValue", e.toString());
							return manipleData;
						}
					});
		}
		return new Gson().toJson(Lists.newArrayList(propertyMap));
	}

	@Override
	public boolean hasStudentPassedSubject(Student student, ExamSubject subject) {
		List<ExamAttendance> attendances = examAttendanceService
				.getExamAttendancesForStudentByExamSubject(subject, student);
		return Iterables.any(attendances, new Predicate<ExamAttendance>() {

			@Override
			public boolean apply(ExamAttendance att) {
				// minimum eine 4 ist benötigt.
				return att.getExamGrade().compareTo(ExamGrade.G40) <= 0;
			}

		});
	}

	@Override
	public Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple() {
		Map<Maniple, Collection<ExamSubject>> subjects = new HashMap<Maniple, Collection<ExamSubject>>();

		for (Maniple m : getManipleDao().findAll()) {
			Collection<ExamSubject> examSubjects = m.getExamSubjects();
			Hibernate.initialize(examSubjects);
			subjects.put(m, examSubjects);
		}

		return subjects;
	}

	/**
	 * @return the examSubjectDao
	 */
	public IExamSubjectDao getExamSubjectDAO() {
		return examSubjectDao;
	}

	/**
	 * @param examSubjectDao
	 *            the examSubjectDao to set
	 */
	public void setExamSubjectDAO(IExamSubjectDao examSubjectDao) {
		this.examSubjectDao = examSubjectDao;
	}

	public IManipleDao getManipleDao() {
		return manipleDao;
	}

	public void setManipleDao(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}

	@Override
	public ExamSubject load(Long id) {
		return examSubjectDao.load(id);
	}

	@Override
	// todo: load und examSubject angleichen
	public ExamSubject getExamSubject(long id) {
		return this.examSubjectDao.load(id);
	}

	@Override
	public long getExamSubjectCount() {
		return examSubjectDao.findAll().size();
	}

	public IExamService getExamService() {
		return examService;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

}
