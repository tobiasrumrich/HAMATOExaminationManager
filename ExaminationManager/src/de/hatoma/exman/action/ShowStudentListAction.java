package de.hatoma.exman.action;

import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IStudentService;

public class ShowStudentListAction extends ActionSupport {
	private static final long serialVersionUID = -7867656427450400882L;
	private IStudentService studentService;
	private Set<Student> students;

	@Override
	public String execute() throws Exception {
		// TODO hal: auskommentiert. wegen compilefehler. listStudents nicht in
		// studentService vorhanden. Hat das überhaupt kompiliert als es
		// committet wurde? oO
		// students = new HashSet<Student>(studentService.listStudents());
		return SUCCESS;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudentss(Set<Student> students) {
		this.students = students;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

}
