package de.hatoma.exman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExaminerDao;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.service.IExaminerService;

@Component
public class ExaminerService implements IExaminerService {

	@Autowired
	private IExaminerDao examinerDao;

	@Override
	public Examiner createExaminer(String forename, String lastname) {
		Examiner examiner = new Examiner();
		examiner.setForename(forename);
		examiner.setLastname(lastname);
		getExaminerDAO().save(examiner);
		return examiner;
	}

	/**
	 * @return the examinerDao
	 */
	public IExaminerDao getExaminerDAO() {
		return examinerDao;
	}

	/**
	 * @param examinerDao
	 *            the examinerDao to set
	 */
	public void setExaminerDAO(IExaminerDao examinerDao) {
		this.examinerDao = examinerDao;
	}

	@Override
	public Examiner load(long id) {
		return examinerDao.load(id);
	}

	@Override
	public List<Examiner> findAll() {
		return examinerDao.findAll();
	}

}
