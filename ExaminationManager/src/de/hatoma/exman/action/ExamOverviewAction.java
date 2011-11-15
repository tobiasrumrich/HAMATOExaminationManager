package de.hatoma.exman.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;

/**
 * Diese Action stellt eine Übersicht der aktuellen Prüfungen bereit
 * 
 * @author tobias
 * 
 */
public class ExamOverviewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IExamService examService;
	
	@Autowired
	private IExamAttendanceService examAttendanceService;
	
	private List<Exam> examList;

	@Override
	public String execute() {
		examList = examService.getExamList();
		
		return "showTable";
	}
	
	public Boolean isExamEditable(long id) {
		Exam exam = examService.getExamById(id);
		List<ExamAttendance> examAttendancesForExam = examAttendanceService.getExamAttendancesForExam(exam);
		
		return (examAttendancesForExam.size() == 0);
	}

	
	/**
	 * @return the examService
	 */
	public IExamService getExamService() {
		return examService;
	}


	/**
	 * @param examService
	 *            the examService to set
	 */
	public void setExamService(IExamService examService) {
		this.examService = examService;
	}


	/**
	 * @return the examList
	 */
	public List<Exam> getExamList() {
		return examList;
	}


	/**
	 * @param examList the examList to set
	 */
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

}
