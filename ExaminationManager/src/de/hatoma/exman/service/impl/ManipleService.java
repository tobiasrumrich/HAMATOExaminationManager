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

		getManipleDAO().save(maniple);
		return maniple;
	}

	@Override
	public Collection<Maniple> getAll() {
		return manipleDao.findAll();
	}

	/**
	 * @return the manipleDao
	 */
	public IManipleDao getManipleDAO() {
		return manipleDao;
	}

	@Override
	public Collection<Student> getStudents(long id) {
		Collection<Student> students;
		students = getManipleDAO().getStudents(id);
		return students;
	}

	/**
	 * @param manipleDao
	 *            the manipleDao to set
	 */
	public void setManipleDAO(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}

	@Override
	public Collection<Student> getStudentsWithPossibleOralExams(long id) {
		Collection<Student> students;
		Collection<Student> oralStudents;
		students = getManipleDAO().getStudents(id);
		
		// letzter Versuch ist 5.0
		// keine zwei Orals
		return students;
		}

}
