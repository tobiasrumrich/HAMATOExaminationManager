package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;

public class SingleExamAttendance extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String display() throws Exception {
		//throw new Exception("ICH GLAUB ES NICHT _ EINE EXCEPTION!!");
		return "showInputForm";
	}
	
	public String getException() throws Exception {
		throw new Exception("ICH GLAUB ES NICHT _ EINE EXCEPTION!!");
	}
	
	public String saveNew() throws Exception {
		
		return "success";
	}

}
