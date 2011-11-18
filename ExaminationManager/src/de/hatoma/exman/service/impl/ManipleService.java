package de.hatoma.exman.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IManipleService;

@Component
public class ManipleService implements IManipleService {

	@Autowired
	private IManipleDao manipleDao;

	@Override
	public Maniple createManiple(StudyBranch studyBranch, int year) {
		Maniple maniple = new Maniple();

		maniple.setStudyBranch(studyBranch);
		maniple.setYear(year);

		getManipleDao().save(maniple);
		return maniple;
	}

	@Override
	public Collection<Maniple> getAll() {
		return manipleDao.findAll();
	}

	public IManipleDao getManipleDao() {
		return manipleDao;
	}

	@Override
	public Collection<Student> getStudents(long id) {
		Collection<Student> students;
		students = getManipleDao().getStudentsForManiple(id);
		return students;
	}


	public void setManipleDao(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}

	@Override
	public long getManipleCount() {
		return manipleDao.findAll().size();
	}

}
