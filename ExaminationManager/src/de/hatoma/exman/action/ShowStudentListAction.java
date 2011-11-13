package de.hatoma.exman.action;

import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IStudentService;
import de.hatoma.exman.service.room.IRoomService;


public class ShowStudentListAction extends ActionSupport {
	private IStudentService studentService;
	private Set<Student> students;

	@Override
	public String execute() throws Exception {
		students = new HashSet<Student>(studentService.listStudents());
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
