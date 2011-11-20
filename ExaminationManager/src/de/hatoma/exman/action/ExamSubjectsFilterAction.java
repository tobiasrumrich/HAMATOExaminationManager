package de.hatoma.exman.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IStudentService;

public class ExamSubjectsFilterAction extends ActionSupport {
	private static final long serialVersionUID = 2484053877817310866L;
	@Autowired
	private IExamSubjectService examSubjectService;
	private Long studentId;
	@Autowired
	private IStudentService studentService;

	public String getAvailableExamSubjectsForStudent() {
		return "json";
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public String getJsonValueString() {
		if (getStudentId() == null) {
			return "";
		}
		return getExamSubjectService().getAvailableExamSubjectsForStudentJson(
				getStudentService().getStudent(getStudentId()));
	}

	public Long getStudentId() {
		return studentId;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

}
