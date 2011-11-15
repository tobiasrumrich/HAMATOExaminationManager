package de.hatoma.exman.action;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;

public class SingleOralExaminationAttendanceAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Maniple> maniples;
	@Autowired
	private IStudentService studentService;
	private long manipleToFetch;
	private long id;
	private Student selectedStudent;
	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}


	private List<Student> students;
	

	@Override
	public String execute() throws Exception {
		return "showInputForm";
	}

	public String showInputForm() throws Exception {
			//TODO: was is wenn ohne id?
			//TODO: was is wenn nich erlaubte id?
			
			selectedStudent = studentService.getStudent(this.id);
		return "showInputForm";
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


}
