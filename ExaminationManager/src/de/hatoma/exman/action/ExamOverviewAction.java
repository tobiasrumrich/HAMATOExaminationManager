package de.hatoma.exman.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;

/**
 * Diese Action stellt eine Übersicht der aktuellen Prüfungen bereit
 * 
 * @author Tobias Rumrich, 3638
 * 
 */
public class ExamOverviewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	private List<Exam> examList;

	@Autowired
	private IExamService examService;


	@Override
	public String execute() {
		examList = examService.getExamList();
		return "showTable";
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @return the examList
	 */
	public List<Exam> getExamList() {
		return examList;
	}

	/**
	 * @return the examService
	 */
	public IExamService getExamService() {
		return examService;
	}

	public Boolean isExamEditable(long id) {
		Exam exam = examService.getExamById(id);

		return examService.isExamEditable(exam);
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	/**
	 * @param examList
	 *            the examList to set
	 */
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	/**
	 * @param examService
	 *            the examService to set
	 */
	public void setExamService(IExamService examService) {
		this.examService = examService;
	}
}
