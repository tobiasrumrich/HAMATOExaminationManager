package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudyBranchDaoTTT;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IStudyBranchService;

@Component
public class StudyBranchService implements IStudyBranchService {

	@Autowired
	private IStudyBranchDaoTTT studyBranchDaoTTT;
	
	@Override
	public StudyBranch createStudyBranch(String shortTag, String longTag,
			String branchName) {
		StudyBranch studyBranch = new StudyBranch();
		studyBranch.setShortTag(shortTag);
		studyBranch.setLongTag(longTag);
		studyBranch.setBranchName(branchName);
		getStudyBranchDAO().save(studyBranch);
		return studyBranch;
	}

	/**
	 * @return the studyBranchDaoTTT
	 */
	public IStudyBranchDaoTTT getStudyBranchDAO() {
		return studyBranchDaoTTT;
	}

	/**
	 * @param studyBranchDaoTTT the studyBranchDaoTTT to set
	 */
	public void setStudyBranchDAO(IStudyBranchDaoTTT studyBranchDaoTTT) {
		this.studyBranchDaoTTT = studyBranchDaoTTT;
	}

}
