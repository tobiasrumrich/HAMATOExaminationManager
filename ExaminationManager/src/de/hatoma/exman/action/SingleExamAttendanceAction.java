package de.hatoma.exman.action;

import java.util.Map;
import java.util.TreeMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.model.ExamGrade;

/**
 * @author Hannes Lemberg 3547
 * 
 */
public class SingleExamAttendanceAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Map<String, String> getAllGrades() {
		TreeMap<String, String> grades = new TreeMap<String, String>();

		ExamGrade[] values = ExamGrade.values();

		for (ExamGrade grade : values) {
			grades.put(grade.toString(), grade.getAsExpression());
		}

		return grades;
	}

	@Override
	public String input() {
		return Action.INPUT;
	}
}
