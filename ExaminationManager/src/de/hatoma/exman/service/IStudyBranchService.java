package de.hatoma.exman.service;

import de.hatoma.exman.model.StudyBranch;

public interface IStudyBranchService {
	/**
	 * Creates a new StudyBranch
	 * 
	 * @param shortTag
	 *            is usually a one letter identifier of the StudyBranch
	 * @param longTag
	 *            is usually a 3-4 letter identifier of the StudyBranch
	 * @param branchName
	 *            is the full name of the StudyBranch
	 * @return
	 */
	public StudyBranch createStudyBranch(String shortTag, String longTag,
			String branchName);

}
