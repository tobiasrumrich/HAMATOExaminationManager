package de.hatoma.exman.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;

public class ExamAttendanceBulkUpdateAction extends ActionSupport implements Preparable {
	

	@Autowired
	private
	IExamAttendanceService examAttendanceService;
	
	@Autowired
	private
	IExamService examService;
	
	// Model Beans
	private List<ExamAttendance> myEntities;
	private Map<String,ExamAttendance> myEntitiesMap;

	public void prepare () {
		// Get the List of MyEntity objects from the datastore.
		Exam exam = getExamService().getExamById(1);
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


}