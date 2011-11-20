package de.hatoma.exman.service;

import java.util.Collection;
import java.util.Map;

import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;

public interface IExamSubjectService {

	Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple();

	Collection<ExamSubject> allSubjectsByManiple(long id);

	ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple);

	ExamSubject getExamSubject(long id);

	public long getExamSubjectCount();

	ExamSubject load(Long id);
}
