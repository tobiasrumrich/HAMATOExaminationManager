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
	private String target;
	private String targetActionName;

	@Override
	public String execute() {
		examList = examService.getExamList();
		if (target != null && target.equals("bulkInsert")) {
			targetActionName = "ExamAttendanceBulkUpdate";
		} else {
			targetActionName = "EditExam";
		}

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

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @return the targetActionName
	 */
	public String getTargetActionName() {
		return targetActionName;
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

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @param targetActionName
	 *            the targetActionName to set
	 */
	public void setTargetActionName(String targetActionName) {
		this.targetActionName = targetActionName;
	}

}
