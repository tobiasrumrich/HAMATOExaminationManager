package de.hatoma.exman.service.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import de.hatoma.exman.dao.IExaminerDao;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.service.IManipleService;

@Component
public class ExaminerService implements IExaminerService {

	@Autowired
	private IExaminerDao examinerDao;
	@Autowired
	private IExamSubjectService examSubjectService;
	@Autowired
	private IManipleService manipleService;
	private Gson gson;

	public ExaminerService() {
		gson = new Gson();
	}

	@Override
	public Examiner createExaminer(String forename, String lastname) {
		Examiner examiner = new Examiner();
		examiner.setForename(forename);
		examiner.setLastname(lastname);
		getExaminerDAO().save(examiner);
		return examiner;
	}

	@Override
	public List<Examiner> findAll() {
		return examinerDao.findAll();
	}

	@Override
	public String getAllExaminersAsJson() {
		List<Examiner> allExaminers = findAll();
		List<Entry<String, String>> s = new ArrayList<Entry<String, String>>();

		for (Examiner e : allExaminers) {
			s.add(new SimpleEntry<String, String>(String.valueOf(e.getId()), e
					.getForename() + " " + e.getLastname()));
		}

		return gson.toJson(s);
	}

	@Override
	public String getAllExamSubjectsJson() {
		Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple = getExamSubjectService()
				.allSubjectsByManiple();
		Map<Long, List<Entry<Long, String>>> examSubjectsByMainple = new HashMap<Long, List<Entry<Long, String>>>();

		for (Entry<Maniple, Collection<ExamSubject>> e : allSubjectsByManiple
				.entrySet()) {
			long manipleId = e.getKey().getId();
			Collection<ExamSubject> subjects = e.getValue();
			List<Entry<Long, String>> subjectIdsAndNames = new ArrayList<Entry<Long, String>>();

			for (ExamSubject es : subjects) {
				subjectIdsAndNames.add(new SimpleEntry<Long, String>(
						es.getId(), es.toString()));
			}

			examSubjectsByMainple.put(manipleId, subjectIdsAndNames);
		}

		return gson.toJson(examSubjectsByMainple);
	}

	@Override
	public String getAllManiplesJson() {
		List<Entry<Long, String>> maniples = new ArrayList<Entry<Long, String>>();
		for (Maniple m : getManipleService().findAll()) {
			maniples.add(new SimpleEntry<Long, String>(m.getId(), m.toString()));
		}
		return gson.toJson(maniples);
	}

	/**
	 * @return the examinerDao
	 */
	public IExaminerDao getExaminerDAO() {
		return examinerDao;
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public IManipleService getManipleService() {
		return manipleService;
	}

	@Override
	public Examiner load(long id) {
		return examinerDao.load(id);
	}

	/**
	 * @param examinerDao
	 *            the examinerDao to set
	 */
	public void setExaminerDAO(IExaminerDao examinerDao) {
		this.examinerDao = examinerDao;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

}
