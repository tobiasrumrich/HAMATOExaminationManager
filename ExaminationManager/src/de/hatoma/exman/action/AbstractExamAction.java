package de.hatoma.exman.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.service.IManipleService;

public abstract class AbstractExamAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String availableExaminersJson;
	private String availableExamSubjectsByManipleJson;
	private String availableManiplesJson;
	protected SimpleDateFormat dateFormat;
	protected String examDateN;
	@Autowired
	protected IExaminerService examinerService;
	protected String examManipleN;
	protected String examManipleNid;
	@Autowired
	protected IExamService examService;
	protected String examSubjectN;
	protected String examSubjectNid;
	@Autowired
	protected IExamSubjectService examSubjectService;

	protected String examType;
	protected String lecturerN;
	protected String lecturerNid;
	@Autowired
	private IManipleService manipleService;

	public AbstractExamAction() {
		super();
	}

	private String getAllExaminersAsJson() {
		return examinerService.getAllExaminersAsJson();
	}

	private String getAllExamSubjectsJson() {
		return examinerService.getAllExamSubjectsJson();
	}

	private String getAllManiplesJson() {
		return manipleService.getAllManiplesJson();
	}

	public String getAvailableExaminersJson() {
		return availableExaminersJson;
	}

	public String getAvailableExamSubjectsByManipleJson() {
		return availableExamSubjectsByManipleJson;
	}

	public String getAvailableManiplesJson() {
		return availableManiplesJson;
	}

	public String getExamDateN() {
		return examDateN;
	}

	public IExaminerService getExaminerService() {
		return examinerService;
	}

	public String getExamManipleN() {
		return examManipleN;
	}

	public String getExamManipleNid() {
		return examManipleNid;
	}

	public IExamService getExamService() {
		return examService;
	}

	public String getExamSubjectN() {
		return examSubjectN;
	}

	public String getExamSubjectNid() {
		return examSubjectNid;
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public String getExamType() {
		return examType;
	}

	public Map<ExamType, String> getExamTypes() {
		Map<ExamType, String> types = new HashMap<ExamType, String>();
		for (Entry<ExamType, String> e : ExamType.keys().entrySet()) {
			types.put(e.getKey(), getText(e.getValue(), "!" + e.getValue()));
		}
		return types;
	}

	public String getLecturerN() {
		return lecturerN;
	}

	public String getLecturerNid() {
		return lecturerNid;
	}

	public IManipleService getManipleService() {
		return manipleService;
	}

	public abstract String getTargetAction();

	public abstract String getTargetMethod();

	public void init() {
		dateFormat = new SimpleDateFormat(getText("examDateFormatNoTimeFormat"));
		this.availableExaminersJson = getAllExaminersAsJson();
		this.availableExamSubjectsByManipleJson = getAllExamSubjectsJson();
		this.availableManiplesJson = getAllManiplesJson();
	}

	protected void notNull(String value, String fieldName) {
		if (value == null || value.trim().length() == 0) {
			addFieldError(fieldName, getText("errorRequired"));
		}
	}

	protected Exam readAndValidateParams() {
		return readAndValidateParams(new Exam());
	}

	protected Exam readAndValidateParams(Exam exam) {
		Date date = null;

		notNull(examDateN, "examDateN");
		notNull(examManipleN, "examManipleN");
		notNull(examSubjectN, "examSubjectN");
		notNull(lecturerN, "lecturerN");

		if (examDateN != null && !examDateN.trim().equals("")) {
			try {
				date = dateFormat.parse(examDateN);
			} catch (ParseException e) {
				String msg = getText("errorDateFailed",
						new String[] { examDateN });
				addFieldError("examDateN", msg);
			}
		}
		exam.setDate(date);
		exam.setExaminer(examinerService.load(Long.valueOf(lecturerNid)));
		exam.setExamSubject(examSubjectService.load(Long
				.valueOf(examSubjectNid)));
		exam.setExamType(ExamType.valueOf(examType));

		return exam;
	}

	public void setAvailableExaminersJson(String availableExaminersJson) {
		this.availableExaminersJson = availableExaminersJson;
	}

	public void setAvailableExamSubjectsByManipleJson(
			String availableExamSubjectsByManipleJson) {
		this.availableExamSubjectsByManipleJson = availableExamSubjectsByManipleJson;
	}

	public void setAvailableManiplesJson(String availableManiplesJson) {
		this.availableManiplesJson = availableManiplesJson;
	}

	public void setExamDateN(String examDateN) {
		this.examDateN = examDateN;
	}

	public void setExaminerService(IExaminerService examinerService) {
		this.examinerService = examinerService;
	}

	public void setExamManipleN(String examManipleN) {
		this.examManipleN = examManipleN;
	}

	public void setExamManipleNid(String examManipleNid) {
		this.examManipleNid = examManipleNid;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	public void setExamSubjectN(String examSubjectN) {
		this.examSubjectN = examSubjectN;
	}

	public void setExamSubjectNid(String examSubjectNid) {
		this.examSubjectNid = examSubjectNid;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public void setLecturerN(String lecturerN) {
		this.lecturerN = lecturerN;
	}

	public void setLecturerNid(String lecturerNid) {
		this.lecturerNid = lecturerNid;
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

}