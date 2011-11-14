package de.hatoma.exman.dao.impl;

import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudyBranchDao;
import de.hatoma.exman.model.StudyBranch;

@Component
public class StudyBranchDao extends BaseDao<StudyBranch> implements
		IStudyBranchDao {

	public StudyBranchDao() {
		super(StudyBranch.class);
	}

}
