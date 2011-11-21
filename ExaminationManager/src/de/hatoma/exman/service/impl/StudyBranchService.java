package de.hatoma.exman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IStudyBranchDao;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IStudyBranchService;

@Component
public class StudyBranchService implements IStudyBranchService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860966614878909855L;
	@Autowired
	private IStudyBranchDao studyBranchDao;

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
	 * @return the studyBranchDao
	 */
	public IStudyBranchDao getStudyBranchDAO() {
		return studyBranchDao;
	}

	/**
	 * @param studyBranchDao
	 *            the studyBranchDao to set
	 */
	public void setStudyBranchDAO(IStudyBranchDao studyBranchDao) {
		this.studyBranchDao = studyBranchDao;
	}

}
