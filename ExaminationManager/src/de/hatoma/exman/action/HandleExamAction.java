package de.hatoma.exman.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class HandleExamAction extends ActionSupport {

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

	@RequiredStringValidator(message = "das erste Feld ist ben�tigt!", key = "i18n.key", shortCircuit = true, trim = true)
	public void setMessage(String message) {
		this.message = message;
	}
}
