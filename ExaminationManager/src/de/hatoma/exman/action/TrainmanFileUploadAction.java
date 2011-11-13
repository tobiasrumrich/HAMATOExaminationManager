package de.hatoma.exman.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.service.ITrainmanService;

public class TrainmanFileUploadAction extends ActionSupport {

	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	
	private ITrainmanService trainmanService;

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String execute() throws Exception {

		System.out.println("BRECHPUNKT!!! upload=" + fileUploadFileName
				+ ";tmpName=" + fileUpload);

		BufferedReader fileReader = new BufferedReader(new FileReader(fileUpload));
		String fileRow = null;
		List<String> transportStatements = new ArrayList<String>();

		while ((fileRow = fileReader.readLine()) != null) {
			System.out.println("Gelesene Zeile: " + fileRow);
			transportStatements.add(fileRow);
		}
		getTrainmanService().transportSQLDumpToSystem(transportStatements);

		return SUCCESS;

	}

	public String display() {
		return "input";
	}

	/**
	 * @return the trainmanService
	 */
	public ITrainmanService getTrainmanService() {
		return trainmanService;
	}

	/**
	 * @param trainmanService the trainmanService to set
	 */
	public void setTrainmanService(ITrainmanService trainmanService) {
		this.trainmanService = trainmanService;
	}

}