package de.hatoma.exman.action;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IManipleService;


public class ShowStudentListAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IManipleService manipleService;
	private List<Student> students;
	
	@Override
	public String execute() throws Exception {
		return "success";
	}
	
	public String display() throws Exception {
		students = (List<Student>) getStudents();
		return "showInputForm";
	}
	public Collection<Student> getStudents() {
		// TODO Hier steht die 1 Just for dummy
		return manipleService.getStudents(1);
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public IManipleService getManipleService() {
		return manipleService;
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

}
