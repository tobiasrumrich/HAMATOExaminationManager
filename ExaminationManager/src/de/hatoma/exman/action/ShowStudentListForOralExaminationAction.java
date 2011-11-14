package de.hatoma.exman.action;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IManipleService;

public class ShowStudentListForOralExaminationAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Maniple> maniples;
	@Autowired
	private IManipleService manipleService;
	private long manipleToFetch;
	private String selectedManiple;
	private List<Student> students;
	

	@Override
	public String execute() throws Exception {
		if (selectedManiple == null || selectedManiple.isEmpty()) {
			manipleToFetch = 1;
		}
		else {
			manipleToFetch = Integer.valueOf(selectedManiple);
		}
		maniples = getManiples();
		students = (List<Student>) manipleService.getStudents(manipleToFetch);
		return "showInputForm";
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

	public Collection<Student> getStudents() {
		return students;
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

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * @return the selectedManiple
	 */
	public String getSelectedManiple() {
		return selectedManiple;
	}

	/**
	 * @param selectedManiple the selectedManiple to set
	 */
	public void setSelectedManiple(String selectedManiple) {
		this.selectedManiple = selectedManiple;
	}

}
