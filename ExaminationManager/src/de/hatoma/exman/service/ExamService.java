package de.hatoma.exman.service;

import java.util.Date;

import de.hatoma.exman.dao.IExamDAO;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;

public class ExamService implements IExamService {

	private IExamDAO examDAO;
	
	@Override
	public Exam createExam(ExamSubject examSubject, Date date, Examiner examiner) {
		Exam exam = new Exam();
		exam.setExamSubject(examSubject);
		exam.setDate(date);
		exam.setExaminer(examiner);
		examDAO.save(exam);
		return exam;
	}

	/**
	 * @return the examDAO
	 */
	public IExamDAO getExamDAO() {
		return examDAO;
	}

	/**
	 * @param examDAO the examDAO to set
	 */
	public void setExamDAO(IExamDAO examDAO) {
		this.examDAO = examDAO;
	}

}
