package de.hatoma.exman.service.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

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
	public String getAllManiplesJson() {
		List<Entry<Long, String>> maniples = new ArrayList<Entry<Long, String>>();
		for (Maniple m : findAll()) {
			maniples.add(new SimpleEntry<Long, String>(m.getId(), m.toString()));
		}
		return new Gson().toJson(maniples);
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
	public Collection<Maniple> findAll() {
		return manipleDao.findAll();
	}

	public long getManipleCount() {
		return manipleDao.findAll().size();
	}

	@Override
	public Maniple load(long id) {
		return manipleDao.load(id);
	}

}
