package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * 
 * @author Marcel Schroeter, 3690
 *
 */
public class ProtocolAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public String save() {
		return SUCCESS;
	}

}
