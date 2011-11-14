package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;

import de.hatoma.exman.model.StudyBranch;

@Component
public class StudyBranchDAO extends BaseDAO<StudyBranch> implements IStudyBranchDAO {

	public StudyBranchDAO() {
		super(StudyBranch.class);
	}

	
}
