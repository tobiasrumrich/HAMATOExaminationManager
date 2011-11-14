package de.hatoma.exman.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.service.IExamService;

@Component
public class ExamService implements IExamService {

	@Autowired
	private IExamDao examDao;
	
	@Override
	public Exam createExam(ExamSubject examSubject, Date date, Examiner examiner) {
		Exam exam = new Exam();
		exam.setExamSubject(examSubject);
		exam.setDate(date);
		exam.setExaminer(examiner);
		examDao.save(exam);
		return exam;
	}

	/**
	 * @return the examDao
	 */
	public IExamDao getExamDAO() {
		return examDao;
	}

	/**
	 * @param examDao the examDao to set
	 */
	public void setExamDAO(IExamDao examDao) {
		this.examDao = examDao;
	}

}
