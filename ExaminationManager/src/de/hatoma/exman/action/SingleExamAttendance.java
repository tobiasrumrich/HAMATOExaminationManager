package de.hatoma.exman.action;

import java.util.Collection;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IStudentService;
import de.hatoma.exman.service.IStudyBranchService;

public class SingleExamAttendance extends ActionSupport {

	private String formStudentId;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IExamAttendanceService examAttendanceService;
	private IStudentService studentService;


	private IStudyBranchService studyBranchService;
	
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
		
	
		
		

		StudyBranch studyBranch = studyBranchService.createStudyBranch("AG","Agrarökonomie");
		
		Maniple maniple = manipleService.createManiple(studyBranch, int year);
		
		Student student = studentService.createStudent("Horst", "Schlemmer", maniple);

		
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

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IStudyBranchService getStudyBranchService() {
		return studyBranchService;
	}

	public void setStudyBranchService(IStudyBranchService studyBranchService) {
		this.studyBranchService = studyBranchService;
	}

}
