package de.hatoma.exman.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamDaoTTT;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.service.IExamService;

@Component
public class ExamService implements IExamService {

	@Autowired
	private IExamDaoTTT examDaoTTT;
	
	@Override
	public Exam createExam(ExamSubject examSubject, Date date, Examiner examiner) {
		Exam exam = new Exam();
		exam.setExamSubject(examSubject);
		exam.setDate(date);
		exam.setExaminer(examiner);
		examDaoTTT.save(exam);
		return exam;
	}

	/**
	 * @return the examDaoTTT
	 */
	public IExamDaoTTT getExamDAO() {
		return examDaoTTT;
	}

	/**
	 * @param examDaoTTT the examDaoTTT to set
	 */
	public void setExamDAO(IExamDaoTTT examDaoTTT) {
		this.examDaoTTT = examDaoTTT;
	}

}
