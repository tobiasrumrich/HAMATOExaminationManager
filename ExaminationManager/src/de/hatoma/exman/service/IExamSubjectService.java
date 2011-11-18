package de.hatoma.exman.service;

import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;

public interface IExamSubjectService {

	ExamSubject createExamSubject(String title, String description,
			String moduleIdentifier, Maniple maniple);
	ExamSubject getExamSubject(long id);
	public long getExamSubjectCount();
}
