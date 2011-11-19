package de.hatoma.exman.action;

import com.opensymphony.xwork2.Action;

import de.hatoma.exman.model.Exam;

public class CreateExamAction extends AbstractExamAction {

	private static final long serialVersionUID = 5425944372766400873L;

	public String create() {
		init();
		return Action.INPUT;
	}

	public String save() {
		init();

		Exam exam = readAndValidateParams();

		if (getFieldErrors() != null && getFieldErrors().size() > 0) {
			return Action.ERROR;
		}
		examService.save(exam);

		addActionMessage(getText("lblExamCreatedSuccess",
				new String[] { String.valueOf(exam.getId()) }));

		return Action.SUCCESS;
	}

	@Override
	public String getTargetAction() {
		return "CreateExam";
	}

	@Override
	public String getTargetMethod() {
		return "save";
	}
}
