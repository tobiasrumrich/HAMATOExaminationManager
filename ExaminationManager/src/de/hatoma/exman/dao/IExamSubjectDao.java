package de.hatoma.exman.dao;

import java.util.List;

import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;

public interface IExamSubjectDao extends IDao<ExamSubject> {

	/**
	 * Liefert die zu einem Manipel gehörenden Module
	 * 
	 * @param maniple
	 * @return
	 */
	List<ExamSubject> findByManiple(Maniple maniple);
}
