package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;

import de.hatoma.exman.model.Examiner;

@Component
public class ExaminerDAO extends BaseDAO<Examiner> implements IExaminerDAO {

	public ExaminerDAO() {
		super(Examiner.class);
		// TODO Auto-generated constructor stub
	}

}
