package de.hatoma.exman.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;

/**
 * 
 * @author tobias
 *
 */
public class ExamAttendanceBulkUpdateAction extends ActionSupport implements Preparable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private
	IExamAttendanceService examAttendanceService;
	
	@Autowired
	private IExamService examService;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IManipleService manipleService;
	
	// Model Beans
	private List<ExamAttendanceBulkUpdateHelperBean> myEntities = new ArrayList<ExamAttendanceBulkUpdateHelperBean>();
	private Map<String,ExamAttendanceBulkUpdateHelperBean> myEntitiesMap = new HashMap<String,ExamAttendanceBulkUpdateHelperBean>();
	
	private List<ExamAttendanceBulkUpdateHelperBean> myEntitiesConfirmations = new ArrayList<ExamAttendanceBulkUpdateHelperBean>();
	
	private long selectedExamId;
	private long selectedManipleId;
	private ExamSubject examSubject;
	private Exam exam;
	
	public void prepare () {
		// TODO Convert HTTP id(String) to long
		selectedExamId = 1L;
		selectedManipleId = 1L;
		//Get the target exam from the service
		exam = examService.getExamById(selectedExamId);
		
		//The ExamSubject
		examSubject = exam.getExamSubject();
		
	
		
		//Get Students for the selected Maniple
		Collection<Student> students = manipleService.getStudents(selectedManipleId);
		
		for (Student student : students) {
		List<ExamAttendance> attendancesOfStudent = examAttendanceService.getExamAttendancesForStudentByExamSubject(examSubject, student);
			int numAttempts = attendancesOfStudent.size();
			ExamAttendance latestAttempt;
			//latestAttempt=null;
				try {
					latestAttempt = examAttendanceService.getLatestExamAttendanceOfStudentByExamSubject(examSubject, student);
				} catch (NoPreviousAttemptException e) {
					latestAttempt = null;
				}
			ExamAttendanceBulkUpdateHelperBean helperBean = new ExamAttendanceBulkUpdateHelperBean(student,numAttempts, latestAttempt);
			myEntities.add(helperBean);
			myEntitiesMap.put(String.valueOf(student.getId()), helperBean);
			
		}
		/*
		// Create a Map using this.myEntities as the basis for it keyed on myEntity.id.
		Map<String,ExamAttendance> myEntitiesMap = new HashMap<String,ExamAttendance> ();
		for (ExamAttendance myEntity : getMyEntities() ) {
			myEntitiesMap.put(String.valueOf(myEntity.getId ()), myEntity);
		}		*/
	}

	public String execute () throws Exception {
		// Iterate over the List of MyEntity objects and persist them using our DAO
		//for (ExamAttendanceBulkUpdateHelperBean myEntity : getMyEntities())   {
			//getExamAttendanceService().update(myEntity);
		//}
		return "showBulkList";
	}
	
	public String insertNewExamAttendances() throws Exception {
		System.out.println("**** CALLED ****");
		for (ExamAttendanceBulkUpdateHelperBean myEntity : getMyEntities())   {
			if (!myEntity.getNewGrade().equals("")) {
				ExamGrade grade = null;
				if (myEntity.getNewGrade().equals("1.0") || myEntity.getNewGrade().equals("1,0")) grade=ExamGrade.G10;
				if (myEntity.getNewGrade().equals("1.3") || myEntity.getNewGrade().equals("1,3")) grade=ExamGrade.G13;
				if (myEntity.getNewGrade().equals("1.7") || myEntity.getNewGrade().equals("1,7")) grade=ExamGrade.G17;
				if (myEntity.getNewGrade().equals("2.0") || myEntity.getNewGrade().equals("2,0")) grade=ExamGrade.G20;
				if (myEntity.getNewGrade().equals("2.3") || myEntity.getNewGrade().equals("2,3")) grade=ExamGrade.G23;
				if (myEntity.getNewGrade().equals("2.7") || myEntity.getNewGrade().equals("2,7")) grade=ExamGrade.G27;
				if (myEntity.getNewGrade().equals("3.0") || myEntity.getNewGrade().equals("3.0")) grade=ExamGrade.G30;
				if (myEntity.getNewGrade().equals("3.3") || myEntity.getNewGrade().equals("3.3")) grade=ExamGrade.G33;
				if (myEntity.getNewGrade().equals("3.7") || myEntity.getNewGrade().equals("3.7")) grade=ExamGrade.G37;
				if (myEntity.getNewGrade().equals("4.0") || myEntity.getNewGrade().equals("4.0")) grade=ExamGrade.G40;
				if (myEntity.getNewGrade().equals("5.0") || myEntity.getNewGrade().equals("5.0")) grade=ExamGrade.G50;
				if (myEntity.getNewGrade().equals("6.0") || myEntity.getNewGrade().equals("6.0")) grade=ExamGrade.G60;
				myEntitiesConfirmations.add(myEntity);
			}
		}
		return "confirmation";
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
	 * @return the examSubject
	 */
	public ExamSubject getExamSubject() {
		return examSubject;
	}

	/**
	 * @param examSubject the examSubject to set
	 */
	public void setExamSubject(ExamSubject examSubject) {
		this.examSubject = examSubject;
	}

	/**
	 * @return the selectedExamId
	 */
	public long getSelectedExamId() {
		return selectedExamId;
	}

	/**
	 * @param selectedExamId the selectedExamId to set
	 */
	public void setSelectedExamId(long selectedExamId) {
		this.selectedExamId = selectedExamId;
	}

	/**
	 * @return the selectedManipleId
	 */
	public long getSelectedManipleId() {
		return selectedManipleId;
	}

	/**
	 * @param selectedManipleId the selectedManipleId to set
	 */
	public void setSelectedManipleId(long selectedManipleId) {
		this.selectedManipleId = selectedManipleId;
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
	 * @return the myEntitiesMap
	 */
	public Map<String,ExamAttendanceBulkUpdateHelperBean> getMyEntitiesMap() {
		return myEntitiesMap;
	}

	/**
	 * @param myEntitiesMap the myEntitiesMap to set
	 */
	public void setMyEntitiesMap(Map<String,ExamAttendanceBulkUpdateHelperBean> myEntitiesMap) {
		this.myEntitiesMap = myEntitiesMap;
	}

	/**
	 * @return the myEntities
	 */
	public List<ExamAttendanceBulkUpdateHelperBean> getMyEntities() {
		return myEntities;
	}

	/**
	 * @param myEntities the myEntities to set
	 */
	public void setMyEntities(List<ExamAttendanceBulkUpdateHelperBean> myEntities) {
		this.myEntities = myEntities;
	}

	/**
	 * @return the exam
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * @param exam the exam to set
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * @return the myEntitiesConfirmations
	 */
	public List<ExamAttendanceBulkUpdateHelperBean> getMyEntitiesConfirmations() {
		return myEntitiesConfirmations;
	}

	/**
	 * @param myEntitiesConfirmations the myEntitiesConfirmations to set
	 */
	public void setMyEntitiesConfirmations(List<ExamAttendanceBulkUpdateHelperBean> myEntitiesConfirmations) {
		this.myEntitiesConfirmations = myEntitiesConfirmations;
	}


}