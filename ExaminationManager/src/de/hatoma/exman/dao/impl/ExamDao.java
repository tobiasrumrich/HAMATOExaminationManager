package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamDaoTTT;
import de.hatoma.exman.model.Exam;

@Component
public class ExamDao extends BaseDao<Exam> implements IExamDaoTTT {

	public ExamDao() {
		super(Exam.class);
		// TODO Auto-generated constructor stub
	}

}
