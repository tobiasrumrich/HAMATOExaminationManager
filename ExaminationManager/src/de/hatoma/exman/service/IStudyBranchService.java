package de.hatoma.exman.service;

import de.hatoma.exman.model.StudyBranch;

public interface IStudyBranchService {
	public StudyBranch createStudyBranch(String shortTag, String longTag,
			String branchName);
}
