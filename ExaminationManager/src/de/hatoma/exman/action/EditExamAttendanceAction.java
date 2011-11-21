package de.hatoma.exman.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.service.IExamAttendanceService;

/**
 * Action zum Bearbeiten einer Teilnahme an einer Prüfung
 * 
 * @author Hannes Lemberg 3547
 * 
 */
public class EditExamAttendanceAction extends ActionSupport implements
		Preparable {

	private static final long serialVersionUID = 2701498037786744476L;
	private SimpleDateFormat dateFormat;
	private Long examAttendanceId;
	@Autowired
	private IExamAttendanceService examAttendanceService;
	private SortedMap<ExamGrade, String> examGrades;
	private String frmExamDate;
	private Examiner frmExamExaminer;
	private String frmExamGrade;
	private String frmExamType;
	private String frmStudent;
	private String frmSubject;
	private String frmSupplementalOralExaminationDate;
	private String frmSupplementalOralExaminationGrade;
	private boolean oralAllowed;
	private SortedMap<OralExamGrade, String> oralExamGrades;

	public String edit() {
		// nur daten befüllen
		if (examAttendanceId == null) {
			addActionError(getText("txtErrorParamsNoExamAttendanceId"));
			return SUCCESS;
		}

		ExamAttendance attendance = examAttendanceService
				.getExamAttendanceById(examAttendanceId);
		Exam exam = attendance.getExam();

		frmStudent = attendance.getStudent().toString();
		frmSubject = exam.getExamSubject().toString();
		frmExamDate = dateFormat.format(exam.getDate());
		frmExamExaminer = exam.getExaminer();
		frmExamType = getText(exam.getExamType().getKey());
		frmExamGrade = attendance.getExamGrade().toString();

		oralAllowed = attendance.getSupplementalOralExamDate() != null;
		if (oralAllowed) {
			frmSupplementalOralExaminationDate = dateFormat.format(attendance
					.getSupplementalOralExamDate());
			frmSupplementalOralExaminationGrade = getText(attendance
					.getSupplementOralExamGrade().toString());
		}

		return Action.INPUT;
	}

	public String execute() {
		return Action.SUCCESS;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public Long getExamAttendanceId() {
		return examAttendanceId;
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public SortedMap<ExamGrade, String> getExamGrades() {
		return examGrades;
	}

	public String getFrmExamDate() {
		return frmExamDate;
	}

	public Examiner getFrmExamExaminer() {
		return frmExamExaminer;
	}

	public String getFrmExamGrade() {
		return frmExamGrade;
	}

	public String getFrmExamType() {
		return frmExamType;
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

	public SortedMap<OralExamGrade, String> getOralExamGrades() {
		return oralExamGrades;
	}

	public boolean isOralAllowed() {
		return oralAllowed;
	}

	@Override
	public void prepare() {
		dateFormat = new SimpleDateFormat(getText("examDateFormatNoTimeFormat"));
		SortedMap<OralExamGrade, String> tempOralExamGrades = new TreeMap<OralExamGrade, String>();
		for (OralExamGrade oralExamGrade : OralExamGrade.values()) {
			tempOralExamGrades.put(oralExamGrade,
					oralExamGrade.getAsExpression());
		}
		oralExamGrades = tempOralExamGrades;
		SortedMap<ExamGrade, String> tempExamGrades = new TreeMap<ExamGrade, String>();
		for (ExamGrade examGrade : ExamGrade.values()) {
			tempExamGrades.put(examGrade, examGrade.getAsExpression());
		}
		examGrades = tempExamGrades;
	}

	public String save() {
		// updaten
		ExamAttendance attendance = examAttendanceService
				.getExamAttendanceById(examAttendanceId);
		attendance.setExamGrade(ExamGrade.valueOf(frmExamGrade));
		oralAllowed = attendance.getSupplementalOralExamDate() != null;
		if (oralAllowed) {
			if (frmSupplementalOralExaminationDate == null
					|| frmSupplementalOralExaminationDate.equals("")) {
				addFieldError("frmSupplementalOralExaminationDate",
						getText("errorRequired"));
				return INPUT;
			}

			try {
				attendance.setSupplementalOralExamDate(dateFormat
						.parse(frmSupplementalOralExaminationDate));
			} catch (ParseException e) {
				addFieldError(
						"frmSupplementalOralExaminationDate",
						getText("errorDateFailed",
								frmSupplementalOralExaminationDate));
				return INPUT;
			}

			attendance.setSupplementOralExamGrade(OralExamGrade
					.valueOf(frmSupplementalOralExaminationGrade));
		}

		examAttendanceService.update(attendance);
		addActionMessage(getText("txtEditExamAttendanceSuccess"));
		return SUCCESS;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setExamAttendanceId(Long examAttendanceId) {
		this.examAttendanceId = examAttendanceId;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	public void setFrmExamDate(String frmExamDate) {
		this.frmExamDate = frmExamDate;
	}

	public void setFrmExamExaminer(Examiner frmExamExaminer) {
		this.frmExamExaminer = frmExamExaminer;
	}

	public void setFrmExamGrade(String frmExamGrade) {
		this.frmExamGrade = frmExamGrade;
	}

	public void setFrmExamType(String frmExamType) {
		this.frmExamType = frmExamType;
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

	public void setOralAllowed(boolean oralAllowed) {
		this.oralAllowed = oralAllowed;
	}
}
