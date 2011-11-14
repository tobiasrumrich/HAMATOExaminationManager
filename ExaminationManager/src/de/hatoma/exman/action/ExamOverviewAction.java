package de.hatoma.exman.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.room.Room;
import de.hatoma.exman.service.IExamService;

/**
 * Diese Action stellt eine Übersicht der aktuellen Prüfungen bereit
 * 
 * @author tobias
 * 
 */
public class ExamOverviewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Set<Room> exams;

	@Autowired
	private IExamService examService;

	@Override
	public String execute() {
		examService.getExamList();
		return "SHOWLIST";

	}

	/**
	 * @return the exams
	 */
	public Set<Room> getExams() {
		return exams;
	}

	/**
	 * @return the examService
	 */
	public IExamService getExamService() {
		return examService;
	}

	/**
	 * @param exams
	 *            the exams to set
	 */
	public void setExams(Set<Room> exams) {
		this.exams = exams;
	}

	/**
	 * @param examService
	 *            the examService to set
	 */
	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

}
