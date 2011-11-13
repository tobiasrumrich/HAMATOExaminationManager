package de.hatoma.exman.service;

import java.util.Collection;
import java.util.HashSet;

import de.hatoma.exman.dao.IManipleDAO;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
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
	
	public Collection<Student> getStudents(long id) {
		Collection<Student> students;
		students = getManipleDAO().getStudents(id);
		return students;
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
