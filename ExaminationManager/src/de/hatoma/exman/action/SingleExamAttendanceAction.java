package de.hatoma.exman.action;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IStudentService;

/**
 * @author Hannes Lemberg 3547
 * 
 */
public class SingleExamAttendanceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String studentN;
	private Long studentNid;
	private Long examSubjectN;
	private Long examN;
	private ExamGrade examGrade;

	@Autowired
	private IStudentService studentService;
	@Autowired
	private IExamSubjectService examSubjectService;
	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExamService examService;

	public Map<String, String> getAllGrades() {
		TreeMap<String, String> grades = new TreeMap<String, String>();

		ExamGrade[] values = ExamGrade.values();

		for (ExamGrade grade : values) {
			grades.put(grade.toString(), grade.getAsExpression());
		}

		return grades;
	}

	public String save() {
		notNull(examN, "examN");
		notNull(examSubjectN, "examSubjectN");
		notNull(studentN, "studentN");

		if (getFieldErrors() != null && getFieldErrors().size() > 0) {
			return Action.INPUT;
		}

		Student student = studentService.getStudent(studentNid);
		Exam exam = examService.load(examN);

		examAttendanceService.createExamAttendanceForStudent(student, exam,
				examGrade);

		addActionMessage(getText("lblExamAttendanceCreated"));
		examGrade = ExamGrade.G10;
		examN = null;
		examSubjectN = null;
		studentN = null;
		studentNid = null;
		return Action.INPUT;
	}

	private void notNull(Object value, String fieldName) {
		if (value == null) {
			addFieldError(fieldName, getText("errorRequired"));
		}
	}

	private void notNull(String value, String fieldName) {
		if (value == null || value.trim().length() == 0) {
			addFieldError(fieldName, getText("errorRequired"));
		}
	}

	public String getAllStudents() {
		return studentService.getAllStudentsAsJson();
	}

	public String getStudentN() {
		return studentN;
	}

	public Long getStudentNid() {
		return studentNid;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public String input() {
		return Action.INPUT;
	}

	public void setStudentN(String studentN) {
		this.studentN = studentN;
	}

	public void setStudentNid(Long studentNid) {
		this.studentNid = studentNid;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public Long getExamSubjectN() {
		return examSubjectN;
	}

	public void setExamSubjectN(Long examSubjectN) {
		this.examSubjectN = examSubjectN;
	}

	public Long getExamN() {
		return examN;
	}

	public void setExamN(Long examN) {
		this.examN = examN;
	}

	public ExamGrade getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(ExamGrade examGrade) {
		this.examGrade = examGrade;
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	public IExamService getExamService() {
		return examService;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}
}
