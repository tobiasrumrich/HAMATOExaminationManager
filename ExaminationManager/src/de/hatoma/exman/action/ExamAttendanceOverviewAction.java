package de.hatoma.exman.action;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.dao.exceptions.InvalidEntityIdException;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;

public class ExamAttendanceOverviewAction extends ActionSupport implements
		Preparable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExamSubjectService examSubjectService;
	private String jsonManipleId;
	private String jsonStudentId;
	private String jsonValueString;
	private String jsonExamAttendanceId;

	@Autowired
	private IManipleService manipleService;

	private Collection<Maniple> maniplesList;

	private String studentsAsJson;

	@Autowired
	private IStudentService studentService;

	@Override
	public String execute() {
		return student();
	}

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @return the examSubjectService
	 */
	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	/**
	 * @return the jsonManipleId
	 */
	public String getJsonManipleId() {
		return jsonManipleId;
	}

	/**
	 * @return the jsonStudentId
	 */
	public String getJsonStudentId() {
		return jsonStudentId;
	}

	/**
	 * @return the jsonValueString
	 */
	public String getJsonValueString() {
		return jsonValueString;
	}

	/**
	 * @return the maniplesList
	 */
	public Collection<Maniple> getManiplesList() {
		return maniplesList;
	}

	/**
	 * @return the studentsAsJson
	 */
	public String getStudentsAsJson() {
		return studentsAsJson;
	}

	public String jsonResponderDeleteExamAttendance() {

		Long deleteId = Long.valueOf(jsonExamAttendanceId);
		try {
			examAttendanceService.delete(deleteId);
		} catch (InvalidEntityIdException e) {
			jsonValueString = "{\"result\":\"error\"}";
			return "json";
		}
		jsonValueString = "{\"result\":\"success\"}";
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
		String pattern = "<p><a href=\"AuditTrail?studentId=_STUDID_&examSubjectId=_SUBJID_\" title=\""
				+ getText("txtShowAuditTrailForThisRecord")
				+ "\" ><img src=\"resources/img/icons/database_key.png\" /></a><a href=\"EditExamAttendance?examAttendanceId=_ATTID_\" title=\""
				+ getText("lblEditExamAttendance")
				+ "\" /><img src=\"resources/img/icons/pencil_go.png\"></a><a href=\"#\" onClick=\"confirmDeleteOfExamAttendance(_ATTID_);\" title=\""
				+ getText("lblDeleteExamAttendance")
				+ "\" /><img src=\"resources/img/icons/cancel.png\"></a></p>";
		jsonValueString = examAttendanceService
				.getAllCurrentExamAttendancesForManipleAsJSON(jsonManiple,
						pattern, getText("examDateFormatNoTimeFormat"));
		return "json";
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
		String pattern = "<p><a href=\"AuditTrail?studentId=_STUDID_&examSubjectId=_SUBJID_\" title=\""
				+ getText("txtShowAuditTrailForThisRecord")
				+ "\" ><img src=\"resources/img/icons/database_key.png\" /></a><a href=\"EditExamAttendance?examAttendanceId=_ATTID_\" title=\""
				+ getText("lblEditExamAttendance")
				+ "\" /><img src=\"resources/img/icons/pencil_go.png\"></a><a href=\"#\" onClick=\"confirmDeleteOfExamAttendance(_ATTID_);\" title=\""
				+ getText("lblDeleteExamAttendance")
				+ "\" /><img src=\"resources/img/icons/cancel.png\"></a></p>";
		jsonValueString = examAttendanceService
				.getAllCurrentExamAttendancesForStudentAsJSON(jsonStudent,
						pattern, getText("examDateFormatNoTimeFormat"));
		return "json";
	}

	public String maniple() {
		maniplesList = manipleService.getAll();
		return "listPerManiple";
	}

	@Override
	public void prepare() throws Exception {

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
	 * @param examSubjectService
	 *            the examSubjectService to set
	 */
	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	/**
	 * @param jsonManipleId
	 *            the jsonManipleId to set
	 */
	public void setJsonManipleId(String jsonManipleId) {
		this.jsonManipleId = jsonManipleId;
	}

	/**
	 * @param jsonStudentId
	 *            the jsonStudentId to set
	 */
	public void setJsonStudentId(String jsonStudentId) {
		this.jsonStudentId = jsonStudentId;
	}

	/**
	 * @param jsonValueString
	 *            the jsonValueString to set
	 */
	public void setJsonValueString(String jsonValueString) {
		this.jsonValueString = jsonValueString;
	}

	/**
	 * @param maniplesList
	 *            the maniplesList to set
	 */
	public void setManiplesList(Collection<Maniple> maniplesList) {
		this.maniplesList = maniplesList;
	}

	/**
	 * @param studentsAsJson
	 *            the studentsAsJson to set
	 */
	public void setStudentsAsJson(String studentsAsJson) {
		this.studentsAsJson = studentsAsJson;
	}

	public String student() {
		studentsAsJson = studentService.getAllStudentsAsJson();
		return "listPerStudent";

	}

	/**
	 * @return the jsonExamAttendanceId
	 */
	public String getJsonExamAttendanceId() {
		return jsonExamAttendanceId;
	}

	/**
	 * @param jsonExamAttendanceId
	 *            the jsonExamAttendanceId to set
	 */
	public void setJsonExamAttendanceId(String jsonExamAttendanceId) {
		this.jsonExamAttendanceId = jsonExamAttendanceId;
	}

}
