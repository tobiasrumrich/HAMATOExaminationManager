package de.hatoma.exman.action;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.service.IStudentService;

/**
 * @author Hannes Lemberg 3547
 * 
 */
public class SingleExamAttendanceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String studentN;
	private Long studentNid;
	@Autowired
	private IStudentService studentService;
	private Long studentId;

	public Map<String, String> getAllGrades() {
		TreeMap<String, String> grades = new TreeMap<String, String>();

		ExamGrade[] values = ExamGrade.values();

		for (ExamGrade grade : values) {
			grades.put(grade.toString(), grade.getAsExpression());
		}

		return grades;
	}

	public String getExamsForStudent() {
		return null;
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
