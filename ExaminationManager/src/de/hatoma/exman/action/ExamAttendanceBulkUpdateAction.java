package de.hatoma.exman.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.action.helpers.ExamAttendanceBulkUpdateHelperBean;
import de.hatoma.exman.dao.exceptions.NoPreviousAttemptException;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IStudentService;

/**
 * This action allows the bulk insertion of ExamAttendances for a specified
 * exam.
 * 
 * @author Tobias Rumrich, 3638
 * 
 */
public class ExamAttendanceBulkUpdateAction extends ActionSupport implements
		Preparable, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exam exam;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	private String examId;

	@Autowired
	private IExamService examService;
	private ExamSubject examSubject;

	// Model Beans
	private List<ExamAttendanceBulkUpdateHelperBean> myEntities = new ArrayList<ExamAttendanceBulkUpdateHelperBean>();

	private List<ExamAttendanceBulkUpdateHelperBean> myEntitiesConfirmations = new ArrayList<ExamAttendanceBulkUpdateHelperBean>();
	private Map<String, ExamAttendanceBulkUpdateHelperBean> myEntitiesMap = new HashMap<String, ExamAttendanceBulkUpdateHelperBean>();
	private Map<String, String[]> parameters;
	// private long selectedManipleId;
	private List<ExamAttendance> protocolledExamAttendances = new ArrayList<ExamAttendance>();

	private long selectedExamId;

	@Autowired
	private IStudentService studentService;

	public String execute() throws Exception {
		if (getActionErrors().size() > 0)
			return "criticalError";
		return "showBulkList";
	}

	/**
	 * @return the exam
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @return the examId
	 */
	public String getExamId() {
		return examId;
	}

	/**
	 * @return the examService
	 */
	public IExamService getExamService() {
		return examService;
	}

	/**
	 * @return the examSubject
	 */
	public ExamSubject getExamSubject() {
		return examSubject;
	}

	/**
	 * @return the myEntities
	 */
	public List<ExamAttendanceBulkUpdateHelperBean> getMyEntities() {
		return myEntities;
	}

	/**
	 * @return the myEntitiesConfirmations
	 */
	public List<ExamAttendanceBulkUpdateHelperBean> getMyEntitiesConfirmations() {
		return myEntitiesConfirmations;
	}

	/**
	 * @return the myEntitiesMap
	 */
	public Map<String, ExamAttendanceBulkUpdateHelperBean> getMyEntitiesMap() {
		return myEntitiesMap;
	}

	public List<ExamAttendance> getProtocolledExamAttendances() {
		return protocolledExamAttendances;
	}

	/**
	 * @return the selectedExamId
	 */
	public long getSelectedExamId() {
		return selectedExamId;
	}

	/**
	 * @return the studentService
	 */
	public IStudentService getStudentService() {
		return studentService;
	}

	/**
	 * Redirects methods, if user presses ENTER to submit form
	 */
	public String input() throws Exception {
		return insertNewExamAttendances();
	}

	public String insertNewExamAttendances() throws Exception {
		
		//Prüfen, ob überhaupt etwas eingegeben wurde
		long numChanges = 0;
		
		for (ExamAttendanceBulkUpdateHelperBean myEntity : getMyEntities()) {
			if (!myEntity.getNewGrade().equals("")) {
				ExamGrade grade = null;
				numChanges++;
				if (myEntity.getNewGrade().equals("1.0")
						|| myEntity.getNewGrade().equals("1,0"))
					grade = ExamGrade.G10;
				if (myEntity.getNewGrade().equals("1.3")
						|| myEntity.getNewGrade().equals("1,3"))
					grade = ExamGrade.G13;
				if (myEntity.getNewGrade().equals("1.7")
						|| myEntity.getNewGrade().equals("1,7"))
					grade = ExamGrade.G17;
				if (myEntity.getNewGrade().equals("2.0")
						|| myEntity.getNewGrade().equals("2,0"))
					grade = ExamGrade.G20;
				if (myEntity.getNewGrade().equals("2.3")
						|| myEntity.getNewGrade().equals("2,3"))
					grade = ExamGrade.G23;
				if (myEntity.getNewGrade().equals("2.7")
						|| myEntity.getNewGrade().equals("2,7"))
					grade = ExamGrade.G27;
				if (myEntity.getNewGrade().equals("3.0")
						|| myEntity.getNewGrade().equals("3,0"))
					grade = ExamGrade.G30;
				if (myEntity.getNewGrade().equals("3.3")
						|| myEntity.getNewGrade().equals("3,3"))
					grade = ExamGrade.G33;
				if (myEntity.getNewGrade().equals("3.7")
						|| myEntity.getNewGrade().equals("3,7"))
					grade = ExamGrade.G37;
				if (myEntity.getNewGrade().equals("4.0")
						|| myEntity.getNewGrade().equals("4,0"))
					grade = ExamGrade.G40;
				if (myEntity.getNewGrade().equals("5.0")
						|| myEntity.getNewGrade().equals("5,0"))
					grade = ExamGrade.G50;
				if (myEntity.getNewGrade().equals("6.0")
						|| myEntity.getNewGrade().equals("6,0"))
					grade = ExamGrade.G60;
				protocolledExamAttendances.add(examAttendanceService
						.createExamAttendanceForStudent(myEntity.getStudent(),
								exam, grade));
			}
		}
		if (numChanges >0) {
		DateFormat dateDay = new SimpleDateFormat(getText("examDateFormatNoTimeFormat"));
		DateFormat dateTime = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		addActionMessage(getText("txtUiProtocolHeader"));
		addActionMessage(getText("lblDate") + ": " + dateDay.format(date));
		addActionMessage(getText("lblTime") + ": " + dateTime.format(date));
		addActionMessage(getText("lblUser") + ": " + currentUser.getUsername());
		return "protocol";
		}
		else {
			addActionMessage(getText("txtYouHaveNotFilledOutAnything"));
			 return "showBulkList";
		}
	}

	/**
	 * This function is called to prepare the action. This happens, because we
	 * have implemented the <Preparable> interface
	 */
	public void prepare() {

		/*
		 * At this point the request parameters have not been injected into the
		 * Actions fields yet. Therefore me have to ask the ActionContext for
		 * the request parameters. We could use: Map<String, Object> parameters
		 * = ActionContext.getContext().getParameters(); but it is better to use
		 * the <ParameterAware> interface, since by that the servlet-config
		 * Interceptor delivers this info. see:
		 * http://struts.apache.org/2.0.14/docs
		 * /how-can-we-access-request-parameters-passed-into-an-action.html
		 */

		if (!(selectedExamId > 0)
				&& (parameters.containsKey("examId") && parameters
						.get("examId") != null)) {

			String[] examIdStrings = this.parameters.get("examId");

			if (examIdStrings[0] != null) {
				selectedExamId = Long.valueOf(examIdStrings[0]);
			} else {
				addActionError(getText("txtErrorParamsNoExamId"));
			}
		} else {
			addActionError(getText("txtErrorParamsNoExamId"));
			return;
		}

		// Get the target exam from the service
		exam = examService.getExamById(selectedExamId);

		// Let us check if the examId served to us by the request is really
		// existing
		if (exam == null) {
			addActionError(getText("txtErrorInvalidExamId"));
			return;
		}

		// If the exam exists (otherwise we would not be at this point) than
		// there must also be an ExamSubject:
		examSubject = exam.getExamSubject();

		// Get the students that are eligible to attend to
		// this exam
		List<Student> students = examAttendanceService
				.getAllStudentsEligibleForExamAttendance(exam);

		// Let's iterate through the list of all eligible students to create
		// the needed helper beans to produce the list form
		for (Student student : students) {
			List<ExamAttendance> attendancesOfStudent = examAttendanceService
					.getExamAttendancesForStudentByExamSubject(examSubject,
							student);

			int numAttempts = attendancesOfStudent.size();
			ExamAttendance latestAttempt;

			// For the display part, we need the grade from the latest exam
			// attempt
			try {
				latestAttempt = examAttendanceService
						.getLatestExamAttendanceOfStudentByExamSubject(
								examSubject, student);
			} catch (NoPreviousAttemptException e) {
				latestAttempt = null;
			}

			ExamAttendanceBulkUpdateHelperBean helperBean = new ExamAttendanceBulkUpdateHelperBean(
					student, numAttempts, latestAttempt);
			myEntities.add(helperBean);
			myEntitiesMap.put(String.valueOf(student.getId()), helperBean);

		}

	}

	/**
	 * @param exam
	 *            the exam to set
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * @param examAttendanceService
	 *            the examAttendanceService to set
	 */
	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	/**
	 * @param examId
	 *            the examId to set
	 */
	public void setExamId(String examId) {
		this.examId = examId;
	}

	/**
	 * @param examService
	 *            the examService to set
	 */
	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	/**
	 * @param examSubject
	 *            the examSubject to set
	 */
	public void setExamSubject(ExamSubject examSubject) {
		this.examSubject = examSubject;
	}

	/**
	 * @param myEntities
	 *            the myEntities to set
	 */
	public void setMyEntities(
			List<ExamAttendanceBulkUpdateHelperBean> myEntities) {
		this.myEntities = myEntities;
	}

	/**
	 * @param myEntitiesConfirmations
	 *            the myEntitiesConfirmations to set
	 */
	public void setMyEntitiesConfirmations(
			List<ExamAttendanceBulkUpdateHelperBean> myEntitiesConfirmations) {
		this.myEntitiesConfirmations = myEntitiesConfirmations;
	}

	/**
	 * @param myEntitiesMap
	 *            the myEntitiesMap to set
	 */
	public void setMyEntitiesMap(
			Map<String, ExamAttendanceBulkUpdateHelperBean> myEntitiesMap) {
		this.myEntitiesMap = myEntitiesMap;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;

	}

	public void setProtocolledExamAttendances(
			List<ExamAttendance> protocolledExamAttendances) {
		this.protocolledExamAttendances = protocolledExamAttendances;
	}

	/**
	 * @param selectedExamId
	 *            the selectedExamId to set
	 */
	public void setSelectedExamId(long selectedExamId) {
		this.selectedExamId = selectedExamId;
	}

	/**
	 * @param studentService
	 *            the studentService to set
	 */
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public void validate() {
		super.validate();
		Boolean foundError = false;
		for (ExamAttendanceBulkUpdateHelperBean bean : myEntities) {
			if (!bean.getNewGrade().equals("")
					&& (!bean.getNewGrade().matches(
							"([123][.,][037])|([456][.,]0)"))) {
				// Feld
				addFieldError("myEntitiesMap['" + bean.getStudent().getId()
						+ "'].newGrade", getText("txtGradeValidationError"));
				// Textausgabe (notwendig, da ' nicht escaped werden kann
				addFieldError("myEntitiesMap[" + bean.getStudent().getId()
						+ "].newGrade", getText("txtGradeValidationError"));
				foundError = true;

			}
		}
		if (foundError) {
			addActionMessage(getText("txtActionInfoGradeValidationError"));
		}

	}

}