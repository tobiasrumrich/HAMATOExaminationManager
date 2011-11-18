package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamSubjectDao;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.service.IExamSubjectService;

@Component
public class ExamSubjectService implements IExamSubjectService {

	@Autowired
	private IExamSubjectDao examSubjectDao;

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

	@Override
	public ExamSubject getExamSubject(long id) {
		return this.examSubjectDao.load(id);
	}

	@Override
	public long getExamSubjectCount() {
		return examSubjectDao.findAll().size();
	}

}
