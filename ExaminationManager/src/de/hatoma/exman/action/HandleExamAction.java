package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class HandleExamAction extends ActionSupport {

	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String save() {
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	@RequiredStringValidator(message = "das erste Feld ist benštigt!", key = "i18n.key", shortCircuit = true, trim = true)
	public void setMessage(String message) {
		this.message = message;
	}
}
