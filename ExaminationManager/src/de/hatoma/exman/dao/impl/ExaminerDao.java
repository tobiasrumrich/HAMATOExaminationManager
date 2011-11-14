package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExaminerDao;
import de.hatoma.exman.model.Examiner;

@Component
public class ExaminerDao extends BaseDao<Examiner> implements IExaminerDao {

	public ExaminerDao() {
		super(Examiner.class);
		// TODO Auto-generated constructor stub
	}

}
