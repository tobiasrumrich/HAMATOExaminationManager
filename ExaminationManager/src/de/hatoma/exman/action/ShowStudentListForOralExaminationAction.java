package de.hatoma.exman.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IManipleService;

public class ShowStudentListForOralExaminationAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ExamAttendance> examAttendances;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	private List<Maniple> maniples;
	@Autowired
	private IManipleService manipleService;
	private long manipleToFetch;

	private String selectedManiple;

	@Override
	public String execute() throws Exception {
		if (selectedManiple == null || selectedManiple.isEmpty()) {
			manipleToFetch = 1;
		} else {
			manipleToFetch = Integer.valueOf(selectedManiple);
		}
		maniples = getManiples();
		examAttendances = (List<ExamAttendance>) getExamAttendanceService().getOralCandidates(manipleToFetch);
		return "showInputForm";
	}
	public List<ExamAttendance> getExamAttendances() {
		return examAttendances;
	}
	/**
	 * @return the allManiples
	 */
	public List<Maniple> getManiples() {
		return (List<Maniple>) manipleService.getAll();
	}
	public IManipleService getManipleService() {
		return manipleService;
	}

	public long getManipleToFetch() {
		return manipleToFetch;
	}

	/**
	 * @return the selectedManiple
	 */
	public String getSelectedManiple() {
		return selectedManiple;
	}

	public void setExamAttendances(List<ExamAttendance> examAttendances) {
		this.examAttendances = examAttendances;
	}

	/**
	 * @param maniples
	 *            the allManiples to set
	 */
	public void setManiples(List<Maniple> maniples) {
		this.maniples = maniples;
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	public void setManipleToFetch(long manipleToFetch) {
		this.manipleToFetch = manipleToFetch;
	}

	/**
	 * @param selectedManiple
	 *            the selectedManiple to set
	 */
	public void setSelectedManiple(String selectedManiple) {
		this.selectedManiple = selectedManiple;
	}
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}
	public void setExamAttendanceService(IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

}
