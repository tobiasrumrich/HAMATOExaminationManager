package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.service.ITrainmanService;

public class TrainmanAction extends ActionSupport {

	private ITrainmanService trainmanService;

	public String insertData() throws Exception {

		getTrainmanService().createPhaseOne();

		return "success";

	}
	
	public String execute() throws Exception {

		return "input";

	}
	

	/**
	 * @return the trainmanService
	 */
	public ITrainmanService getTrainmanService() {
		return trainmanService;
	}

	/**
	 * @param trainmanService
	 *            the trainmanService to set
	 */
	public void setTrainmanService(ITrainmanService trainmanService) {
		this.trainmanService = trainmanService;
	}

}