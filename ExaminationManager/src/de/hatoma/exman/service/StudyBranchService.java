package de.hatoma.exman.service;

import de.hatoma.exman.dao.IStudyBranchDAO;
import de.hatoma.exman.model.StudyBranch;

public class StudyBranchService implements IStudyBranchService {

	private IStudyBranchDAO studyBranchDAO;
	
	@Override
	public StudyBranch createStudyBranch(String shortTag, String longTag,
			String branchName) {
		StudyBranch studyBranch = new StudyBranch();
		studyBranch.setShortTag(shortTag);
		studyBranch.setLongTag(longTag);
		studyBranch.setBranchName(branchName);
		getStudyBranchDAO().save(studyBranch);
		return null;
	}

	/**
	 * @return the studyBranchDAO
	 */
	public IStudyBranchDAO getStudyBranchDAO() {
		return studyBranchDAO;
	}

	/**
	 * @param studyBranchDAO the studyBranchDAO to set
	 */
	public void setStudyBranchDAO(IStudyBranchDAO studyBranchDAO) {
		this.studyBranchDAO = studyBranchDAO;
	}

}
