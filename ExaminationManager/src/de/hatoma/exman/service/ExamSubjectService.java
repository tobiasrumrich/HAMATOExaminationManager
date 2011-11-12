package de.hatoma.exman.service;

import de.hatoma.exman.dao.IExamSubjectDAO;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;

public class ExamSubjectService implements IExamSubjectService{

	private IExamSubjectDAO examSubjectDAO;
	
	@Override
	public ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple) {
		ExamSubject examSubject = new ExamSubject();
		examSubject.setTitle(title);
		examSubject.setDescription(description);
		examSubject.setModuleIdentifier(moduleIdentifier);
		examSubject.setManiple(maniple);
		examSubjectDAO.save(examSubject);
		return examSubject;
	}

	/**
	 * @return the examSubjectDAO
	 */
	public IExamSubjectDAO getExamSubjectDAO() {
		return examSubjectDAO;
	}

	/**
	 * @param examSubjectDAO the examSubjectDAO to set
	 */
	public void setExamSubjectDAO(IExamSubjectDAO examSubjectDAO) {
		this.examSubjectDAO = examSubjectDAO;
	}

}
