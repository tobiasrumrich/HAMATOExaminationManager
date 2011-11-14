package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamSubjectDaoTTT;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.service.IExamSubjectService;

@Component
public class ExamSubjectService implements IExamSubjectService{

	@Autowired
	private IExamSubjectDaoTTT examSubjectDaoTTT;
	
	@Override
	public ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple) {
		ExamSubject examSubject = new ExamSubject();
		examSubject.setTitle(title);
		examSubject.setDescription(description);
		examSubject.setModuleIdentifier(moduleIdentifier);
		examSubject.setManiple(maniple);
		examSubjectDaoTTT.save(examSubject);
		return examSubject;
	}

	/**
	 * @return the examSubjectDaoTTT
	 */
	public IExamSubjectDaoTTT getExamSubjectDAO() {
		return examSubjectDaoTTT;
	}

	/**
	 * @param examSubjectDaoTTT the examSubjectDaoTTT to set
	 */
	public void setExamSubjectDAO(IExamSubjectDaoTTT examSubjectDaoTTT) {
		this.examSubjectDaoTTT = examSubjectDaoTTT;
	}

}
