package de.hatoma.exman.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.action.helpers.ExamAttendanceBulkUpdateHelperBean;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
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
	private List<ExamAttendance> myEntities;
	private Map<String,ExamAttendanceBulkUpdateHelperBean> myEntitiesMap;
	
	private long selectedExamId;
	private long selectedManipleId;

	private ExamSubject examSubject;

	public void prepare () {
		// TODO Convert HTTP id(String) to long
		selectedExamId = 3L;
		
		//Get the target exam from the service
		Exam exam = examService.getExamById(selectedExamId);
		
		//The ExamSubject
		examSubject = exam.getExamSubject();
		
		//examAttendanceService.getExamAttendancesForExam(exam);
		List<ExamAttendance> previousExamAttendances = examAttendanceService.getExamAttendancesByExamSubject(examSubject);
		
		//Get Students for the selected Maniple
		Collection<Student> students = manipleService.getStudents(selectedManipleId);
		
		for (Student student : students) {
		List<ExamAttendance> attendancesOfStudent = examAttendanceService.getExamAttendancesForStudentByExamSubject(examSubject, student);
			int numAttempts = attendancesOfStudent.size();
			numAttendances = attendancesOfStudent.size();
			if (attendancesOfStudent.size() == 0) {
				numAttempts=0;
			}
			e
			ExamAttendanceBulkUpdateHelperBean(student,numAttempts,);
			
		}
		
		
		//studentService.getStudent(id);
		setMyEntities(getExamAttendanceService().getExamAttendancesForExam(exam));

		// Create a Map using this.myEntities as the basis for it keyed on myEntity.id.
		Map<String,ExamAttendance> myEntitiesMap = new HashMap<String,ExamAttendance> ();
		for (ExamAttendance myEntity : getMyEntities() ) {
			myEntitiesMap.put(String.valueOf(myEntity.getId ()), myEntity);
		}		
	}

	public String execute () throws Exception {
		// Iterate over the List of MyEntity objects and persist them using our DAO
		for (ExamAttendance myEntity : getMyEntities()  ) {
			getExamAttendanceService().update(myEntity);
		}
		return "showBulkList";
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
	 * @return the myEntities
	 */
	public List<ExamAttendance> getMyEntities() {
		return myEntities;
	}

	/**
	 * @param myEntities the myEntities to set
	 */
	public void setMyEntities(List<ExamAttendance> myEntities) {
		this.myEntities = myEntities;
	}

	/**
	 * @return the myEntitiesMap
	 */
	public Map<String,ExamAttendance> getMyEntitiesMap() {
		return myEntitiesMap;
	}

	/**
	 * @param myEntitiesMap the myEntitiesMap to set
	 */
	public void setMyEntitiesMap(Map<String,ExamAttendance> myEntitiesMap) {
		this.myEntitiesMap = myEntitiesMap;
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


}