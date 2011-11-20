package de.hatoma.exman.action;

import com.opensymphony.xwork2.Action;

import de.hatoma.exman.model.Exam;

public class EditExamAction extends AbstractExamAction {

	private static final long serialVersionUID = 5425944372766400873L;
	private Long examId;

	public String edit() {
		init();
		Exam exam = examService.load(getExamId());
		this.examDateN = dateFormat.format(exam.getDate());
		this.examManipleN = exam.getExamSubject().getManiple().toString();
		this.examManipleNid = String.valueOf(exam.getExamSubject().getManiple()
				.getId());
		this.examSubjectN = exam.getExamSubject().toString();
		this.examSubjectNid = String.valueOf(exam.getExamSubject().getId());
		this.examType = exam.getExamType().toString();
		this.lecturerN = exam.getExaminer().toString();
		this.lecturerNid = String.valueOf(exam.getExaminer().getId());
		return Action.INPUT;
	}

	public Long getExamId() {
		return examId;
	}

	@Override
	public String getTargetAction() {
		return "EditExam";
	}

	@Override
	public String getTargetMethod() {
		return "update";
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String update() {
		init();
		Exam exam = examService.load(getExamId());
		exam = readAndValidateParams(exam);

		if (getFieldErrors() != null && getFieldErrors().size() > 0) {
			return Action.ERROR;
		}
		examService.update(exam);

		addActionMessage(getText("lblExamUpdatedSuccess",
				new String[] { String.valueOf(exam.getId()) }));

		return Action.SUCCESS;

	}
}
