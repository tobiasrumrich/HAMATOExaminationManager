package de.hatoma.exman.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.service.IManipleService;

public abstract class AbstractExamAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String availableExaminersJson;
	private String availableExamSubjectsByManipleJson;
	private String availableManiplesJson;
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
	protected SimpleDateFormat dateFormat;

	public AbstractExamAction() {
		super();
	}

	private String getAllExaminers() {
		List<Examiner> allExaminers = examinerService.findAll();
		List<Entry<String, String>> s = new ArrayList<Entry<String, String>>();

		for (Examiner e : allExaminers) {
			s.add(new SimpleEntry<String, String>(String.valueOf(e.getId()), e
					.getForename() + " " + e.getLastname()));
		}
		String json = new Gson().toJson(s);
		return json;
	}

	private String getAllExamSubjects() {
		Map<Maniple, Collection<ExamSubject>> allSubjectsByManiple = examSubjectService
				.allSubjectsByManiple();
		Map<Long, List<Entry<Long, String>>> examSubjectsByMainple = new HashMap<Long, List<Entry<Long, String>>>();

		for (Entry<Maniple, Collection<ExamSubject>> e : allSubjectsByManiple
				.entrySet()) {
			long manipleId = e.getKey().getId();
			Collection<ExamSubject> subjects = e.getValue();
			List<Entry<Long, String>> subjectIdsAndNames = new ArrayList<Entry<Long, String>>();

			for (ExamSubject es : subjects) {
				subjectIdsAndNames.add(new SimpleEntry<Long, String>(
						es.getId(), es.toString()));
			}

			examSubjectsByMainple.put(manipleId, subjectIdsAndNames);
		}

		return new Gson().toJson(examSubjectsByMainple);
	}

	private String getAllManiples() {
		List<Entry<Long, String>> maniples = new ArrayList<Entry<Long, String>>();
		for (Maniple m : manipleService.findAll()) {
			maniples.add(new SimpleEntry<Long, String>(m.getId(), m.toString()));
		}
		return new Gson().toJson(maniples);
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

	public void init() {
		dateFormat = new SimpleDateFormat(getText("examDateFormatNoTimeFormat"));
		this.availableExaminersJson = getAllExaminers();
		this.availableExamSubjectsByManipleJson = getAllExamSubjects();
		this.availableManiplesJson = getAllManiples();
	}

	protected void notNull(String value, String fieldName) {
		if (value == null || value.trim().length() == 0) {
			addFieldError(fieldName, getText("errorRequired"));
		}
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

	public abstract String getTargetAction();

	public abstract String getTargetMethod();

}