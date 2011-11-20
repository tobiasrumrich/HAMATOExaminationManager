package de.hatoma.exman.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import de.hatoma.exman.action.interceptors.ITrainmanInterceptorInterconnect;
import de.hatoma.exman.service.ITrainmanService;

/**
 * This is the action that allows to initialize the database with default
 * respective generated initial values.
 * 
 * @author Tobias Rumrich, 3638
 * 
 */
public class TrainmanAction extends ActionSupport implements
		ITrainmanInterceptorInterconnect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, String> creationMethods;

	private int maxStudentsPerManiple = 130;

	private int minStudentsPerManiple = 90;
	private String selectedMethod;

	@Autowired
	private ITrainmanService trainmanService;

	/**
	 * @return the creationMethods
	 */
	public Map<String, String> getCreationMethods() {
		return creationMethods;
	}

	/**
	 * @return the maxStudentsPerManiple
	 */
	public int getMaxStudentsPerManiple() {
		return maxStudentsPerManiple;
	}

	/**
	 * @return the minStudentsPerManiple
	 */
	public int getMinStudentsPerManiple() {
		return minStudentsPerManiple;
	}

	/**
	 * @return the selectedMethod
	 */
	public String getSelectedMethod() {
		return selectedMethod;
	}

	/**
	 * @return the selectedMethods
	 */
	public String getSelectedMethods() {
		return selectedMethod;
	}

	/**
	 * Starts processing to insert data to the database
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insertData() throws Exception {

		if (!trainmanService.doesDatabaseComplyWithRequirements()) {
		if (selectedMethod.equals("complete")) {
			trainmanService.completeInitializatain(minStudentsPerManiple,
					maxStudentsPerManiple);
			return "success";
		} else if (selectedMethod.equals("examOnly")) {
			trainmanService.bootAndExams(minStudentsPerManiple,
					maxStudentsPerManiple);
			return "success";
		}

		else if (selectedMethod.equals("bootstrapper")) {
			trainmanService.bootStrapper(minStudentsPerManiple,
					maxStudentsPerManiple);
			return "success";
		}

		return "input";
		}
		else{
			addActionError(getText("txtTrainmanErrorMayNotProvideServiceNow"));
			return "error";
		}

	}

	/**
	 * @param creationMethods
	 *            the creationMethods to set
	 */
	public void setCreationMethods(Map<String, String> creationMethods) {
		this.creationMethods = creationMethods;
	}

	/**
	 * @param maxStudentsPerManiple
	 *            the maxStudentsPerManiple to set
	 */
	@IntRangeFieldValidator(key = "errorInt", shortCircuit = true, min = "6", max = "30000")
	@RequiredFieldValidator(type = ValidatorType.FIELD, shortCircuit = true, key = "errorRequired")
	public void setMaxStudentsPerManiple(String maxStudentsPerManiple) {
		this.maxStudentsPerManiple = Integer.valueOf(maxStudentsPerManiple);
	}

	/**
	 * @param minStudentsPerManiple
	 *            the minStudentsPerManiple to set
	 */
	@IntRangeFieldValidator(key = "errorInt", shortCircuit = true, min = "3", max = "29997")
	public void setMinStudentsPerManiple(String minStudentsPerManiple) {
		this.minStudentsPerManiple = Integer.valueOf(minStudentsPerManiple);
	}

	/**
	 * @param selectedMethod
	 *            the selectedMethod to set
	 */
	@RequiredFieldValidator(type = ValidatorType.FIELD, shortCircuit = true, key = "errorRequired")
	public void setSelectedMethod(String selectedMethod) {
		this.selectedMethod = selectedMethod;
	}

	/**
	 * @param selectedMethods
	 *            the selectedMethods to set
	 */
	@RequiredFieldValidator(type = ValidatorType.FIELD, shortCircuit = true, key = "errorRequired")
	public void setSelectedMethods(String selectedMethods) {
		this.selectedMethod = selectedMethods;
	}

	/**
	 * @param trainmanService
	 *            the trainmanService to set
	 */
	public void setTrainmanService(ITrainmanService trainmanService) {
		this.trainmanService = trainmanService;
	}

}