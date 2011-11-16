package de.hatoma.exman.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.service.ITrainmanService;

public class TrainmanAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITrainmanService trainmanService;

	private String createExamAttendances;
	
	@Override
	public String execute() throws Exception {

		return "input";

	}

	/**
	 * @return the trainmanService
	 */
	public ITrainmanService getTrainmanService() {
		return trainmanService;
	}

	public String insertData() throws Exception {
		Boolean doCreateExamAttendances = false;
		if (createExamAttendances.equals("true")) {
			doCreateExamAttendances = true;
		}
		getTrainmanService().createPhaseOne(90, 120,doCreateExamAttendances);

		return "success";

	}

	/**
	 * @param trainmanService
	 *            the trainmanService to set
	 */
	public void setTrainmanService(ITrainmanService trainmanService) {
		this.trainmanService = trainmanService;
	}

	/**
	 * @return the createExamAttendances
	 */
	public String getCreateExamAttendances() {
		return createExamAttendances;
	}

	/**
	 * @param createExamAttendances the createExamAttendances to set
	 */
	public void setCreateExamAttendances(String createExamAttendances) {
		this.createExamAttendances = createExamAttendances;
	}

}