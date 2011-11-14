package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;

import de.hatoma.exman.model.Exam;

@Component
public class ExamDAO extends BaseDAO<Exam> implements IExamDAO {

	public ExamDAO() {
		super(Exam.class);
		// TODO Auto-generated constructor stub
	}

}
