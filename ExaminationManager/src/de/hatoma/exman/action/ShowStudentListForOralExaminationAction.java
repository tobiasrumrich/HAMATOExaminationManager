package de.hatoma.exman.action;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IManipleService;


public class ShowStudentListForOralExaminationAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IManipleService manipleService;
	private int selectedManiple;
	private List<Student> students;
	private List<Maniple> maniples;
	
	
	public String display() throws Exception {
		return null;

	}
	
	@Override
	public String execute() throws Exception {
		maniples = getManiples();
		students = (List<Student>) getStudents();
		return "showInputForm";
	}
	public IManipleService getManipleService() {
		return manipleService;
	}

	public Collection<Student> getStudents() {
		return manipleService.getStudents(selectedManiple);
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * @return the allManiples
	 */
	public List<Maniple> getManiples() {
		return (List<Maniple>) manipleService.getAll();
	}

	/**
	 * @param maniples the allManiples to set
	 */
	public void setManiples(List<Maniple> maniples) {
		this.maniples = maniples;
	}

}
