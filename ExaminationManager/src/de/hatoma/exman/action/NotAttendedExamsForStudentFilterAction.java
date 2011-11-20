package de.hatoma.exman.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IStudentService;

public class NotAttendedExamsForStudentFilterAction extends ActionSupport {
	private static final long serialVersionUID = 2484053877817310866L;
	@Autowired
	private IExamService examService;
	private Long examSubjectId;
	@Autowired
	private IExamSubjectService examSubjectService;
	private Long studentId;
	@Autowired
	private IStudentService studentService;

	public IExamService getExamService() {
		return examService;
	}

	public String getExamsForStudent() {
		return "json";
	}

	public Long getExamSubjectId() {
		return examSubjectId;
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public String getJsonValueString() {
		if (getStudentId() == null || getExamSubjectId() == null) {
			return "";
		}

		return getExamService().getAttendableExamsForStudentJson(
				studentService.getStudent(getStudentId()),
				examSubjectService.load(getExamSubjectId()),
				new Function<String, String>() {

					@Override
					public String apply(String key) {
						return getText(key);
					}
				});
	}

	public Long getStudentId() {
		return studentId;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	public void setExamSubjectId(Long examSubjectId) {
		this.examSubjectId = examSubjectId;
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
