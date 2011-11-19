package de.hatoma.exman.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ExamType implements Serializable {
	OralExam("txtExamTypeOralExam"), SeminarPaper("txtExamTypeSeminarPaper"), WrittenExam(
			"txtExamTypeWrittenExam");

	private String key;

	ExamType(String value) {
		this.key = value;
	}

	public String getKey() {
		return key;
	}

	public static Map<ExamType, String> keys() {
		Map<ExamType, String> s = new HashMap<ExamType, String>();
		ExamType[] values = values();
		Arrays.sort(values);
		for (ExamType t : values) {
			s.put(t, t.getKey());
		}
		return s;
	}
}
