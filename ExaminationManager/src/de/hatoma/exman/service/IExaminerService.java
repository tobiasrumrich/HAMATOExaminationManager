package de.hatoma.exman.service;

import java.util.List;

import de.hatoma.exman.model.Examiner;

public interface IExaminerService {
	public Examiner createExaminer(String forename, String lastname);

	public List<Examiner> findAll();

	public String getAllExaminersAsJson();

	public String getAllExamSubjectsJson();

	public String getAllManiplesJson();

	public Examiner load(long id);
}
