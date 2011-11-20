package de.hatoma.exman.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.service.IExamSubjectService;

@Component
public class ExamSubjectService implements IExamSubjectService {

	@Autowired
	private IExamSubjectDao examSubjectDao;

	@Autowired
	private IManipleDao manipleDao;

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
	public Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple() {
		Map<Maniple, Collection<ExamSubject>> subjects = new HashMap<Maniple, Collection<ExamSubject>>();

		for (Maniple m : getManipleDao().findAll()) {
			Collection<ExamSubject> examSubjects = m.getExamSubjects();
			Hibernate.initialize(examSubjects);
			subjects.put(m, examSubjects);
		}

		return subjects;
	}
	
	@Override
	public Collection<ExamSubject> allSubjectsByManiple(long id) {
		Maniple m = manipleDao.load(id);
			Collection<ExamSubject> examSubjects = m.getExamSubjects();
			Hibernate.initialize(examSubjects);

		return examSubjects;
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

}
