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

		getTrainmanService().createPhaseOne(90, 120);

		return "success";

	}

	/**
	 * @param trainmanService
	 *            the trainmanService to set
	 */
	public void setTrainmanService(ITrainmanService trainmanService) {
		this.trainmanService = trainmanService;
	}

}