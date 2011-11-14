package de.hatoma.exman.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IManipleDaoTTT;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IManipleService;

@Component
public class ManipleService implements IManipleService {
	
	@Autowired
	private IManipleDaoTTT manipleDaoTTT;

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
	
	@Override
	public Collection<Maniple> getAll() {
		return manipleDaoTTT.findAll();
	}

	/**
	 * @return the manipleDaoTTT
	 */
	public IManipleDaoTTT getManipleDAO() {
		return manipleDaoTTT;
	}

	/**
	 * @param manipleDaoTTT the manipleDaoTTT to set
	 */
	public void setManipleDAO(IManipleDaoTTT manipleDaoTTT) {
		this.manipleDaoTTT = manipleDaoTTT;
	}



}
