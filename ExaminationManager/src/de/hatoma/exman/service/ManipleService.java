package de.hatoma.exman.service;

import de.hatoma.exman.dao.IManipleDAO;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.StudyBranch;

public class ManipleService implements IManipleService {
	
	private IManipleDAO manipleDAO;

	@Override
	public Maniple createManiple(StudyBranch studyBranch, int year) {
		Maniple maniple = new Maniple();
		
		maniple.setStudyBranch(studyBranch);
		maniple.setYear(year);
		
		getManipleDAO().save(maniple);
		return maniple;
	}

	/**
	 * @return the manipleDAO
	 */
	public IManipleDAO getManipleDAO() {
		return manipleDAO;
	}

	/**
	 * @param manipleDAO the manipleDAO to set
	 */
	public void setManipleDAO(IManipleDAO manipleDAO) {
		this.manipleDAO = manipleDAO;
	}

}
