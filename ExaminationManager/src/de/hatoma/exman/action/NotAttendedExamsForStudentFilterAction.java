package de.hatoma.exman.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IStudentService;

public class NotAttendedExamsForStudentFilterAction extends ActionSupport {
	private static final long serialVersionUID = 2484053877817310866L;
	private Long studentId;
	private Long examSubjectId;
	@Autowired
	private IExamSubjectService examSubjectService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IExamService examService;

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

	public String getExamsForStudent() {
		return "json";
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public Long getExamSubjectId() {
		return examSubjectId;
	}

	public void setExamSubjectId(Long examSubjectId) {
		this.examSubjectId = examSubjectId;
	}

	public IExamService getExamService() {
		return examService;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

}
