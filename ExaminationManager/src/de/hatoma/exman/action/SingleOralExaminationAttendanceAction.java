package de.hatoma.exman.action;

import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IStudentService;

public class SingleOralExaminationAttendanceAction extends ActionSupport {
	/**
	 * author marcel
	 */
	private static final long serialVersionUID = 1L;
	private long examSubjectId;
	@Autowired
	private IExamSubjectService examSubjectService;
	private List<Maniple> maniples;
	private long manipleToFetch;
	private ExamSubject selectedExamSubject;
	private Student selectedStudent;
	private long studentId;
	private List<Student> students;
	private SortedMap<ExamGrade, String> examGrades;


	public void setExamGrades(SortedMap<ExamGrade, String> examGrades) {
		this.examGrades = examGrades;
	}


	public SortedMap<ExamGrade, String> getExamGrades() {
		return this.examGrades;
	}


	@Autowired
	private IStudentService studentService;

	@Override
	public String execute() throws Exception {
		return "showInputForm";
	}


	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public ExamSubject getSelectedExamSubject() {
		return selectedExamSubject;
	}
	

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public long getStudentId() {
		return studentId;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public long getExamSubjectId() {
		return examSubjectId;
	}


	public void setExamSubjectId(long examSubjectId) {
		this.examSubjectId = examSubjectId;
	}


	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public void setSelectedExamSubject(ExamSubject selectedExamSubject) {
		this.selectedExamSubject = selectedExamSubject;
	}
	

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}


	public String showInputForm() throws Exception {
			//TODO: was is wenn ohne id?
			//TODO: was is wenn nich erlaubte id?
		SortedMap<ExamGrade, String> tempExamGrades = new TreeMap<ExamGrade, String>();
		for (ExamGrade examGrade : ExamGrade.values()) {
			tempExamGrades.put(examGrade, examGrade.getAsExpression());
		} 
		examGrades = tempExamGrades;
			selectedStudent = studentService.getStudent(this.studentId);
			selectedExamSubject = examSubjectService.getExamSubject(this.examSubjectId);
		return "showInputForm";
	}


}
