package de.hatoma.exman.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;

public class SingleExamAttendance extends ActionSupport {

	private String formStudentId;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IExamAttendanceService examAttendanceService;
	
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	public String display() throws Exception {
		return "showInputForm";
	}
	
	
	
	@SuppressWarnings("deprecation")
	public String save() throws Exception {
		
		Student student = new Student();
		student.setForename("Horst");
		student.setLastname("Schlemmer");
		student.setManiple(new Maniple());
		student.setId(42);
		
		Exam exam = new Exam();
		exam.setDate(new Date(2011,11,1));
		exam.setExaminer(new Examiner("Ralf","Kesten"));
		exam.setExamSubject(new ExamSubject());
		exam.setId(42);
		
		ExamGrade examGrade = ExamGrade.G10;
		examAttendanceService.createExamAttendanceForStudent(student, exam, examGrade);
		return "success";
	}

	/**
	 * @return the student
	 */
	public String getStudent() {
		return formStudentId;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(String student) {
		this.formStudentId = student;
	}

}
