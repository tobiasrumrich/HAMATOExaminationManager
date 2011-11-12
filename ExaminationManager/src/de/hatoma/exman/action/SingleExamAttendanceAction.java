package de.hatoma.exman.action;

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
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;
import de.hatoma.exman.service.IStudyBranchService;

public class SingleExamAttendanceAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private IExamAttendanceService examAttendanceService;
	
	private String formStudentId;
	private IManipleService manipleService;
	private IStudentService studentService;
	private IStudyBranchService studyBranchService;


	private IExaminerService examinerService;


	private IExamSubjectService examSubjectService;


	private IExamService examService;
	
	public String display() throws Exception {
		return "showInputForm";
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @return the manipleService
	 */
	public IManipleService getManipleService() {
		return manipleService;
	}
	
	
	
	/**
	 * @return the student
	 */
	public String getStudent() {
		return formStudentId;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public IStudyBranchService getStudyBranchService() {
		return studyBranchService;
	}

	@SuppressWarnings("deprecation")
	public String save() throws Exception {
		
	
		
		

		StudyBranch studyBranch = studyBranchService.createStudyBranch("A","Agra","Agrarökonomie");
		
		Maniple maniple = getManipleService().createManiple(studyBranch, 2012);
		
		Student student = studentService.createStudent("Horst", "Schlemmer", maniple);

		Examiner examiner = getExaminerService().createExaminer("Ralf", "Kesten");
		ExamSubject examSubject = getExamSubjectService().createExamSubject("Ausdruckstanz","A Fancy Description","ADT",maniple);
		
		Exam exam = getExamService().createExam(examSubject, new Date(2011,11,1), examiner);

		
		ExamGrade examGrade = ExamGrade.G10;
		examAttendanceService.createExamAttendanceForStudent(student, exam, examGrade);
		return "success";
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	/**
	 * @param manipleService the manipleService to set
	 */
	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(String student) {
		this.formStudentId = student;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public void setStudyBranchService(IStudyBranchService studyBranchService) {
		this.studyBranchService = studyBranchService;
	}

	/**
	 * @return the examService
	 */
	public IExamService getExamService() {
		return examService;
	}

	/**
	 * @param examService the examService to set
	 */
	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	/**
	 * @return the examSubjectService
	 */
	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	/**
	 * @param examSubjectService the examSubjectService to set
	 */
	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	/**
	 * @return the examinerService
	 */
	public IExaminerService getExaminerService() {
		return examinerService;
	}

	/**
	 * @param examinerService the examinerService to set
	 */
	public void setExaminerService(IExaminerService examinerService) {
		this.examinerService = examinerService;
	}

}
