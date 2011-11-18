package de.hatoma.exman.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IManipleService;

public class OralExaminationAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long examAttendanceId;

	private List<ExamAttendance> examAttendances;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	@Autowired
	private IExamSubjectService examSubjectService;
	private String frmSupplementalOralExaminationDate;

	private String frmSupplementalOralExaminationGrade;

	private List<Maniple> maniples;
	@Autowired
	private IManipleService manipleService;
	private long manipleToFetch;
	private SortedMap<OralExamGrade, String> oralExamGrades;
	private List<ExamAttendance> protocolledExamAttendances = new ArrayList<ExamAttendance>();
	private ExamAttendance selectedExamAttendance;
	private ExamSubject selectedExamSubject;

	private String selectedManiple;

	private Student selectedStudent;
	private List<Student> students;

	@Override
	public String execute() throws Exception {
		showList();
		return "showList";
	}

	public long getExamAttendanceId() {
		return examAttendanceId;
	}

	public List<ExamAttendance> getExamAttendances() {
		return examAttendances;
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}
	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public String getFrmSupplementalOralExaminationDate() {
		return frmSupplementalOralExaminationDate;
	}

	public String getFrmSupplementalOralExaminationGrade() {
		return frmSupplementalOralExaminationGrade;
	}

	/**
	 * @return the allManiples
	 */
	public List<Maniple> getManiples() {
		return maniples;
	}

	public IManipleService getManipleService() {
		return manipleService;
	}

	public long getManipleToFetch() {
		return manipleToFetch;
	}

	public SortedMap<OralExamGrade, String> getOralExamGrades() {
		return oralExamGrades;
	}

	public ExamAttendance getSelectedExamAttendance() {
		return selectedExamAttendance;
	}
	public ExamSubject getSelectedExamSubject() {
		return selectedExamSubject;
	}

	/**
	 * @return the selectedManiple
	 */
	public String getSelectedManiple() {
		return selectedManiple;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public String input() throws Exception {
		// TODO: was is wenn ohne id?
		// TODO: was is wenn nich erlaubte id?

		SortedMap<OralExamGrade, String> tempOralExamGrades = new TreeMap<OralExamGrade, String>();
		for (OralExamGrade oralExamGrade : OralExamGrade.values()) {
			tempOralExamGrades.put(oralExamGrade, oralExamGrade.getAsExpression());
		}
		oralExamGrades = tempOralExamGrades;
		selectedExamAttendance = examAttendanceService
				.getExamAttendanceById(this.examAttendanceId);
		selectedStudent = selectedExamAttendance.getStudent();
		selectedExamSubject = selectedExamAttendance.getExam().getExamSubject();

		return "input";
	}

	public String save() throws Exception {
		System.out.println(frmSupplementalOralExaminationDate);
		System.out.println(frmSupplementalOralExaminationGrade);
		ExamAttendance examAttendance;
		OralExamGrade oralExamGrade;
		Calendar calendar = Calendar.getInstance();
		try {
			
			examAttendance = examAttendanceService.getExamAttendanceById(examAttendanceId);
			oralExamGrade = OralExamGrade.valueOf(frmSupplementalOralExaminationGrade);
			int date = Integer.valueOf(frmSupplementalOralExaminationDate.substring(0, 2));
			int month = Integer.valueOf(frmSupplementalOralExaminationDate.substring(3, 5))-1;
			int year = Integer.valueOf(frmSupplementalOralExaminationDate.substring(6, 10));			
			calendar.set(year, month, date);
			
			
			examAttendanceService.addOralExaminationResultToExamAttendance(examAttendance, oralExamGrade, calendar.getTime());
			getProtocolledExamAttendances().add(examAttendance);
		} catch (Exception e) {
			// TODO text i18n oder wie dat heißt
			addActionError("Für diesen Studenten und die Prüfung existiert bereits eine mündliche Ergänzungsnote im System!");
			return "FAIL";
		}
		System.out.println("BAM!");
		return "protocol";

	}

	public void setExamAttendanceId(long examAttendanceId) {
		this.examAttendanceId = examAttendanceId;
	}

	public void setExamAttendances(List<ExamAttendance> examAttendances) {
		this.examAttendances = examAttendances;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public void setFrmSupplementalOralExaminationDate(
			String frmSupplementalOralExaminationDate) {
		this.frmSupplementalOralExaminationDate = frmSupplementalOralExaminationDate;
	}

	public void setFrmSupplementalOralExaminationGrade(
			String frmSupplementalOralExaminationGrade) {
		this.frmSupplementalOralExaminationGrade = frmSupplementalOralExaminationGrade;
	}

	/**
	 * @param maniples
	 *            the allManiples to set
	 */
	public void setManiples(List<Maniple> maniples) {
		this.maniples = maniples;
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	public void setManipleToFetch(long manipleToFetch) {
		this.manipleToFetch = manipleToFetch;
	}

	public void setOralExamGrades(SortedMap<OralExamGrade, String> oralExamGrades) {
		this.oralExamGrades = oralExamGrades;
	}

	public void setSelectedExamAttendance(ExamAttendance selectedExamAttendance) {
		this.selectedExamAttendance = selectedExamAttendance;
	}

	public void setSelectedExamSubject(ExamSubject selectedExamSubject) {
		this.selectedExamSubject = selectedExamSubject;
	}

	/**
	 * @param selectedManiple
	 *            the selectedManiple to set
	 */
	public void setSelectedManiple(String selectedManiple) {
		this.selectedManiple = selectedManiple;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void showList() {
		if (selectedManiple == null || selectedManiple.isEmpty()) {
			manipleToFetch = 1;
		} else {
			manipleToFetch = Integer.valueOf(selectedManiple);
		}
		maniples = (List<Maniple>) manipleService.getAll();
		examAttendances = (List<ExamAttendance>) getExamAttendanceService()
				.getOralCandidates(manipleToFetch);
	}

	public List<ExamAttendance> getProtocolledExamAttendances() {
		return protocolledExamAttendances;
	}

	public void setProtocolledExamAttendances(
			List<ExamAttendance> protocolledExamAttendances) {
		this.protocolledExamAttendances = protocolledExamAttendances;
	}

}
