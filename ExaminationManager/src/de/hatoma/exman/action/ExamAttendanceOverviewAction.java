package de.hatoma.exman.action;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;

public class ExamAttendanceOverviewAction extends ActionSupport implements
		Preparable {

	private static final long serialVersionUID = 1L;

	private String jsonValueString;
	private String jsonStudentId;
	private String jsonManipleId;
	private String studentsAsJson;
	private Collection<Maniple> maniplesList;

	@Autowired
	private IExamSubjectService examSubjectService;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private IManipleService manipleService;

	public String student() {
		studentsAsJson = studentService.getAllStudentsAsJson();
		return "listPerStudent";

	}

	public String maniple() {
		maniplesList = manipleService.getAll();
		return "listPerManiple";
	}

	public void prepare() throws Exception {

	}

	public String execute() {
		return student();
	}

	public String jsonResponderStudent() {
		Student jsonStudent;
		try {
			jsonStudent = studentService
					.getStudent(Long.valueOf(jsonStudentId));
		} catch (Exception e) {
			jsonValueString = "{}";
			return "json";
		}
		String pattern = "<a href=\"AuditTrail?studentId="
				+ jsonStudentId
				+ "&examSubjectId=_ID_\"><img src=\"resources/img/icons/database_key.png\" /> "
				+ getText("txtViewAuditTrail") + "</a>";
		jsonValueString = examAttendanceService
				.getAllCurrentExamAttendancesForStudentAsJSON(jsonStudent,
						pattern);
		return "json";
	}

	public String jsonResponderManiple() {
		Maniple jsonManiple;
		try {
			jsonManiple = manipleService.getById(Long.valueOf(jsonManipleId));
		} catch (Exception e) {
			jsonValueString = "{}";
			return "json";
		}
		String pattern = "<a href=\"AuditTrail?studentId=_STUDID_&examSubjectId=_SUBJID_\"><img src=\"resources/img/icons/database_key.png\" /></a>";
		jsonValueString = examAttendanceService
				.getAllCurrentExamAttendancesForManipleAsJSON(jsonManiple,
						pattern, getText("examDateFormatNoTimeFormat"));
		return "json";
	}

	/**
	 * @return the jsonValueString
	 */
	public String getJsonValueString() {
		return jsonValueString;
	}

	/**
	 * @param jsonValueString
	 *            the jsonValueString to set
	 */
	public void setJsonValueString(String jsonValueString) {
		this.jsonValueString = jsonValueString;
	}

	/**
	 * @return the jsonStudentId
	 */
	public String getJsonStudentId() {
		return jsonStudentId;
	}

	/**
	 * @param jsonStudentId
	 *            the jsonStudentId to set
	 */
	public void setJsonStudentId(String jsonStudentId) {
		this.jsonStudentId = jsonStudentId;
	}

	/**
	 * @return the examSubjectService
	 */
	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	/**
	 * @param examSubjectService
	 *            the examSubjectService to set
	 */
	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
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
	 * @return the studentsAsJson
	 */
	public String getStudentsAsJson() {
		return studentsAsJson;
	}

	/**
	 * @param studentsAsJson
	 *            the studentsAsJson to set
	 */
	public void setStudentsAsJson(String studentsAsJson) {
		this.studentsAsJson = studentsAsJson;
	}

	/**
	 * @return the maniplesList
	 */
	public Collection<Maniple> getManiplesList() {
		return maniplesList;
	}

	/**
	 * @param maniplesList
	 *            the maniplesList to set
	 */
	public void setManiplesList(Collection<Maniple> maniplesList) {
		this.maniplesList = maniplesList;
	}

	/**
	 * @return the jsonManipleId
	 */
	public String getJsonManipleId() {
		return jsonManipleId;
	}

	/**
	 * @param jsonManipleId
	 *            the jsonManipleId to set
	 */
	public void setJsonManipleId(String jsonManipleId) {
		this.jsonManipleId = jsonManipleId;
	}

}
