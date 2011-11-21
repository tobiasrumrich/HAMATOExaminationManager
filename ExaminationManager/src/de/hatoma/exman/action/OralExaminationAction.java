package de.hatoma.exman.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.dao.exceptions.OralGradeAlreadyExistantException;
import de.hatoma.exman.dao.exceptions.StudentNotEligibleForOralExamException;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IManipleService;

/**
 * 
 * @author Marcel Schroeter, 3690
 * 
 */
public class OralExaminationAction extends ActionSupport implements Preparable {
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
	private String frmStudent;
	private String frmSubject;
	private String frmSupplementalOralExaminationDate;
	private String frmSupplementalOralExaminationGrade;

	private List<Maniple> maniples;

	@Autowired
	private IManipleService manipleService;

	private long manipleToFetch;

	private SortedMap<OralExamGrade, String> oralExamGrades;

	private List<ExamAttendance> protocolledExamAttendances = new ArrayList<ExamAttendance>();
	private String selectedManiple;
	private List<Student> students;

	@Override
	public String execute() throws Exception {
		//Liste aller passenden Studenten vorbereiten
		retrieveList();
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

	public String getFrmStudent() {
		return frmStudent;
	}

	public String getFrmSubject() {
		return frmSubject;
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

	public List<ExamAttendance> getProtocolledExamAttendances() {
		return protocolledExamAttendances;
	}

	/**
	 * @return the selectedManiple
	 */
	public String getSelectedManiple() {
		return selectedManiple;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	/**
	 * 
	 */
	@Override
	public String input() throws Exception {
		ExamAttendance selectedExamAttendance;
		try {
			Long.valueOf(examAttendanceId);
		} catch (Exception e) {
			addActionError(getText("txtErrorInvalidExamAttendanceId"));
			return "error";
		}

		selectedExamAttendance = examAttendanceService
				.getExamAttendanceById(this.examAttendanceId);
		if (selectedExamAttendance == null) {
			addActionError(getText("txtErrorInvalidExamAttendanceId"));
			return "error";
		}

		Student selectedStudent = selectedExamAttendance.getStudent();
		ExamSubject selectedExamSubject = selectedExamAttendance.getExam()
				.getExamSubject();

		frmStudent = selectedStudent.getForename() + " "
				+ selectedStudent.getLastname();
		frmSubject = selectedExamSubject.getTitle();
		return "input";
	}

	@Override
	public void prepare() throws Exception {
		SortedMap<OralExamGrade, String> tempOralExamGrades = new TreeMap<OralExamGrade, String>();
		for (OralExamGrade oralExamGrade : OralExamGrade.values()) {
			tempOralExamGrades.put(oralExamGrade,
					oralExamGrade.getAsExpression());
		}
		oralExamGrades = tempOralExamGrades;

	}

	public String save() throws Exception {
		// Validierung
		ExamAttendance examAttendance;
		OralExamGrade oralExamGrade;
		//Datumsformat korrekt?
		SimpleDateFormat dateFormat;
		Date date = null;
		dateFormat = new SimpleDateFormat(getText("examDateFormatNoTimeFormat"));
		
		try {
			date = dateFormat.parse(frmSupplementalOralExaminationDate);
		} catch (Exception e) {
			addActionError(getText("txtErrorWrongDateFormat"));
			return "input";
		}
		Date currentDate = new Date();
		//Datum scheint korrekt formatiert zu sein
		//Liegt es auch nicht in der Zukunft?
		if (date.after(currentDate)) {
			addActionError(getText("txtErrorDateInFuture"));
			return "input";
		}

		// Speichern mit Abfangen der möglichen Exceptions.
		// Exceptions sind sprechend benannt.

		try {
			examAttendance = examAttendanceService
					.getExamAttendanceById(examAttendanceId);
			oralExamGrade = OralExamGrade
					.valueOf(frmSupplementalOralExaminationGrade);
			examAttendanceService.addOralExaminationResultToExamAttendance(
					examAttendance, oralExamGrade, date);
			getProtocolledExamAttendances().clear();
			getProtocolledExamAttendances().add(
					examAttendanceService
							.getExamAttendanceById(examAttendanceId));
		} catch (StudentNotEligibleForOralExamException e) {
			addActionError(getText("txtErrorStudentNotEligibleForOralExam"));
			return "input";
		} catch (OralGradeAlreadyExistantException e) {
			addActionError(getText("txtErrorOralGradeAlreadyExistant"));
			return "input";
		} catch (Exception e) {
			addActionError(getText("txtCommonError"));
			return "input";
		}

		// Systeminformationen abrufen und in brauchbares Format bringen
		// als da wären: ein Datum
		DateFormat dateDay = new SimpleDateFormat(getText("examDateFormatNoTimeFormat"));
		DateFormat dateTime = new SimpleDateFormat("HH:mm");
		// und der aktuelle Benutzername
		UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addActionMessage(getText("txtUiProtocolHeader"));
		// Alles beisammen? Dann können wir ja ein paar Nachrichten feuern
		addActionMessage(getText("lblDate") + ": " + dateDay.format(currentDate));		
		addActionMessage(getText("lblTime") + ": " + dateTime.format(currentDate));
		addActionMessage(getText("lblUser") + ": " + currentUser.getUsername());
		// und zum Protokoll abspringen
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

	public void setFrmStudent(String frmStudent) {
		this.frmStudent = frmStudent;
	}

	public void setFrmSubject(String frmSubject) {
		this.frmSubject = frmSubject;
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

	public void setOralExamGrades(
			SortedMap<OralExamGrade, String> oralExamGrades) {
		this.oralExamGrades = oralExamGrades;
	}

	public void setProtocolledExamAttendances(
			List<ExamAttendance> protocolledExamAttendances) {
		this.protocolledExamAttendances = protocolledExamAttendances;
	}

	/**
	 * @param selectedManiple
	 *            the selectedManiple to set
	 */
	public void setSelectedManiple(String selectedManiple) {
		this.selectedManiple = selectedManiple;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	/**
	 * Diese Methode stellt die Liste aller mündlichen Kandidaten zum momentan gewählten Manipel bereit
	 */
	public void retrieveList() {
		//ist schon ein Manipel ausgewählt? Wenn nicht nimm die 1.
		if (selectedManiple == null || selectedManiple.isEmpty()) {
			manipleToFetch = 1;
		} else {
			manipleToFetch = Integer.valueOf(selectedManiple);
		}
		maniples = (List<Maniple>) manipleService.getAll();
		// und jetzt alle Mündlichen Kandidaten beim Service erfragen
		examAttendances = getExamAttendanceService().getOralCandidates(
				manipleToFetch);
	}

}
