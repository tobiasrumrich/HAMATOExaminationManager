package de.hatoma.exman.dao;

import java.util.List;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;

/**
 * @author Hannes Lemberg 3547
 * 
 */
public interface IExamDao extends IDao<Exam> {
	/**
	 * Liefert alle Prüfungen für das übergebene
	 * 
	 * @param subject
	 *            Fach zurück
	 * @return Liste mit den Prüfungen
	 */
	List<Exam> findAllForSubject(ExamSubject subject);
}
