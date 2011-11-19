package de.hatoma.exman.service;

import java.util.Collection;
import java.util.Map;

import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;

public interface IExamSubjectService {

	ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple);

	Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple();

	ExamSubject load(Long id);

	ExamSubject getExamSubject(long id);

	public long getExamSubjectCount();
}
