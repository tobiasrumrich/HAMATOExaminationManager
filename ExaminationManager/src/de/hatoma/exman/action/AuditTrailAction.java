package de.hatoma.exman.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;

/**
 * This action displays
 * 
 * @author tobias
 * 
 */
public class AuditTrailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ExamAttendance> attendancesList;

	private List<ExamAttendance> examAttendances;

	@Autowired
	private IExamAttendanceService examAttendanceService;

	private Map<String, Exam> examMap;

	private ExamSubject examSubject;
	private String examSubjectId;
	@Autowired
	private IExamSubjectService examSubjectService;
	@Autowired
	private IManipleService manipleService;

	private Map<String, List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> map;
	private long selectedExamSubjectId;

	private long selectedStudentId;
	private Student student;
	private String studentId;

	@Autowired
	private IStudentService studentService;

	@Override
	public String execute() {

		try {
			selectedStudentId = Long.valueOf(studentId);
		} catch (Exception e) {
			addActionError("Ungültige Student ID");
			return "error";
		}

		student = studentService.getStudent(selectedStudentId);
		if (student == null) {
			addActionError("Ungültige Student ID");
			return "error";
		}

		try {
			selectedExamSubjectId = Long.valueOf(examSubjectId);
		} catch (Exception e) {
			addActionError("Ungültige Prüfungsfach ID");
			return "error";
		}

		examSubject = examSubjectService.getExamSubject(selectedExamSubjectId);
		if (examSubject == null) {
			addActionError("Ungültige Prüfungsfach ID");
			return "error";
		}

		map = new HashMap<String, List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>>();
		examMap = new HashMap<String, Exam>();
		examAttendances = examAttendanceService
				.getExamAttendancesForStudentByExamSubject(examSubject, student);
		for (ExamAttendance examAttendance : examAttendances) {
			List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> auditTrail = examAttendanceService
					.getAuditTrail(examAttendance.getId());
			map.put(String.valueOf(examAttendance.getId()), auditTrail);
			examMap.put(String.valueOf(examAttendance.getId()),
					examAttendance.getExam());
		}

		return "displayExamAttendanceAuditTrail";
	}

	/**
	 * @return the attendancesList
	 */
	public List<ExamAttendance> getAttendancesList() {
		return attendancesList;
	}

	/**
	 * @return the examAttendances
	 */
	public List<ExamAttendance> getExamAttendances() {
		return examAttendances;
	}

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @return the examMap
	 */
	public Map<String, Exam> getExamMap() {
		return examMap;
	}

	/**
	 * @return the examSubject
	 */
	public ExamSubject getExamSubject() {
		return examSubject;
	}

	/**
	 * @return the examSubjectId
	 */
	public String getExamSubjectId() {
		return examSubjectId;
	}

	/**
	 * @return the examSubjectService
	 */
	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	/**
	 * @return the manipleService
	 */
	public IManipleService getManipleService() {
		return manipleService;
	}

	/**
	 * @return the map
	 */
	public Map<String, List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> getMap() {
		return map;
	}

	/**
	 * @return the selectedExamSubjectId
	 */
	public long getSelectedExamSubjectId() {
		return selectedExamSubjectId;
	}

	/**
	 * @return the seletedStudentId
	 */
	public long getSeletedStudentId() {
		return selectedStudentId;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @return the studentService
	 */
	public IStudentService getStudentService() {
		return studentService;
	}

	/**
	 * @param attendancesList
	 *            the attendancesList to set
	 */
	public void setAttendancesList(List<ExamAttendance> attendancesList) {
		this.attendancesList = attendancesList;
	}

	/**
	 * @param examAttendances
	 *            the examAttendances to set
	 */
	public void setExamAttendances(List<ExamAttendance> examAttendances) {
		this.examAttendances = examAttendances;
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
	 * @param examMap
	 *            the examMap to set
	 */
	public void setExamMap(Map<String, Exam> examMap) {
		this.examMap = examMap;
	}

	/**
	 * @param examSubject
	 *            the examSubject to set
	 */
	public void setExamSubject(ExamSubject examSubject) {
		this.examSubject = examSubject;
	}

	/**
	 * @param examSubjectId
	 *            the examSubjectId to set
	 */
	public void setExamSubjectId(String examSubjectId) {
		this.examSubjectId = examSubjectId;
	}

	/**
	 * @param examSubjectService
	 *            the examSubjectService to set
	 */
	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	/**
	 * @param manipleService
	 *            the manipleService to set
	 */
	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(
			Map<String, List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> map) {
		this.map = map;
	}

	/**
	 * @param selectedExamSubjectId
	 *            the selectedExamSubjectId to set
	 */
	public void setSelectedExamSubjectId(long selectedExamSubjectId) {
		this.selectedExamSubjectId = selectedExamSubjectId;
	}

	/**
	 * @param seletedStudentId
	 *            the seletedStudentId to set
	 */
	public void setSeletedStudentId(long seletedStudentId) {
		this.selectedStudentId = seletedStudentId;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @param studentService
	 *            the studentService to set
	 */
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

}
