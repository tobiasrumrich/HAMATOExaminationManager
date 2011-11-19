package de.hatoma.exman.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.dao.helpers.AuditTrailBean;
import de.hatoma.exman.model.ExManRevisionEntity;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IStudentService;

/**
 * This action displays 
 * @author tobias
 *
 */
public class AuditTrailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IExamSubjectService examSubjectService;
	
	@Autowired 
	private IExamAttendanceService examAttendanceService;
	
	@Autowired
	private IStudentService studentService;

	private String studentId;
	private String examSubjectId;
	private long selectedStudentId;
	private long selectedExamSubjectId;

	private Student student;

	private ExamSubject examSubject;

	private Map<String,List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> combinedAuditTrail;

	private List<ExamAttendance> examAttendances;
	
	private List<List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> myMapList;

	public String execute() {
		
		try {
			selectedStudentId = Long.valueOf(studentId);
		}
		catch (Exception e) {
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
		}
		catch (Exception e) {
			addActionError("Ungültige Prüfungsfach ID");
		}
		
		examSubject = examSubjectService.getExamSubject(selectedExamSubjectId);
		if (examSubject == null) {
			addActionError("Ungültige Prüfungsfach ID");
		}
		
		// ****** TEST **********

		
		examAttendances = examAttendanceService.getExamAttendancesForStudentByExamSubject(examSubject, student);
		myMapList = new ArrayList<List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>>();
		combinedAuditTrail = new HashMap<String, List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>>();
		for (ExamAttendance examAttendance : examAttendances) {
			//examAttendance.setExamGrade(ExamGrade.G30);
			//examAttendanceService.createExamAttendanceForStudent(examAttendance.getStudent(), examAttendance.getExam(), ExamGrade.G10);
			try {
				examAttendanceService.update(examAttendance);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			examAttendance = examAttendanceService.getExamAttendanceById(examAttendance.getId());
			List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>> auditTrail = examAttendanceService.getAuditTrail(examAttendance.getId());
			combinedAuditTrail.put(String.valueOf(examAttendance.getId()),auditTrail);
			myMapList.add(auditTrail);
		}
		return "displayExamAttendanceAuditTrail";
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
	 * @return the seletedStudentId
	 */
	public long getSeletedStudentId() {
		return selectedStudentId;
	}

	/**
	 * @param seletedStudentId
	 *            the seletedStudentId to set
	 */
	public void setSeletedStudentId(long seletedStudentId) {
		this.selectedStudentId = seletedStudentId;
	}

	/**
	 * @return the selectedExamSubjectId
	 */
	public long getSelectedExamSubjectId() {
		return selectedExamSubjectId;
	}

	/**
	 * @param selectedExamSubjectId
	 *            the selectedExamSubjectId to set
	 */
	public void setSelectedExamSubjectId(long selectedExamSubjectId) {
		this.selectedExamSubjectId = selectedExamSubjectId;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the examSubjectId
	 */
	public String getExamSubjectId() {
		return examSubjectId;
	}

	/**
	 * @param examSubjectId
	 *            the examSubjectId to set
	 */
	public void setExamSubjectId(String examSubjectId) {
		this.examSubjectId = examSubjectId;
	}

	/**
	 * @return the studentService
	 */
	public IStudentService getStudentService() {
		return studentService;
	}

	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @return the examAttendanceService
	 */
	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	/**
	 * @param examAttendanceService the examAttendanceService to set
	 */
	public void setExamAttendanceService(IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}


	/**
	 * @return the examAttendances
	 */
	public List<ExamAttendance> getExamAttendances() {
		return examAttendances;
	}

	/**
	 * @param examAttendances the examAttendances to set
	 */
	public void setExamAttendances(List<ExamAttendance> examAttendances) {
		this.examAttendances = examAttendances;
	}

	/**
	 * @return the combinedAuditTrail
	 */
	public Map<String,List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> getCombinedAuditTrail() {
		return combinedAuditTrail;
	}

	/**
	 * @param combinedAuditTrail the combinedAuditTrail to set
	 */
	public void setCombinedAuditTrail(Map<String,List<AuditTrailBean<ExManRevisionEntity, ExamAttendance>>> combinedAuditTrail) {
		this.combinedAuditTrail = combinedAuditTrail;
	}
}
