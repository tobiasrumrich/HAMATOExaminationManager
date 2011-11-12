package de.hatoma.exman.service;

import de.hatoma.exman.dao.IExaminerDAO;
import de.hatoma.exman.model.Examiner;

public class ExaminerService implements IExaminerService {

	private IExaminerDAO examinerDAO;
	
	@Override
	public Examiner createExaminer(String forename, String lastname) {
		Examiner examiner = new Examiner();
		examiner.setForename(forename);
		examiner.setLastname(lastname);
		getExaminerDAO().save(examiner);
		return examiner;
	}

	/**
	 * @return the examinerDAO
	 */
	public IExaminerDAO getExaminerDAO() {
		return examinerDAO;
	}

	/**
	 * @param examinerDAO the examinerDAO to set
	 */
	public void setExaminerDAO(IExaminerDAO examinerDAO) {
		this.examinerDAO = examinerDAO;
	}

}
