package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExaminerDaoTTT;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.service.IExaminerService;

@Component
public class ExaminerService implements IExaminerService {

	@Autowired
	private IExaminerDaoTTT examinerDaoTTT;
	
	@Override
	public Examiner createExaminer(String forename, String lastname) {
		Examiner examiner = new Examiner();
		examiner.setForename(forename);
		examiner.setLastname(lastname);
		getExaminerDAO().save(examiner);
		return examiner;
	}

	/**
	 * @return the examinerDaoTTT
	 */
	public IExaminerDaoTTT getExaminerDAO() {
		return examinerDaoTTT;
	}

	/**
	 * @param examinerDaoTTT the examinerDaoTTT to set
	 */
	public void setExaminerDAO(IExaminerDaoTTT examinerDaoTTT) {
		this.examinerDaoTTT = examinerDaoTTT;
	}

}
