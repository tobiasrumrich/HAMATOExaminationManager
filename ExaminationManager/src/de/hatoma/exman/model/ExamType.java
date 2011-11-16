package de.hatoma.exman.model;

import java.io.Serializable;

public enum ExamType implements Serializable {
	OralExam("Mündliche Prüfung"), SeminarPaper("Hausarbeit"), WrittenExam(
			"Klausur");

	private String key;

	ExamType(String value) {
		this.key = value;
	}

	public String getKey() {
		return key;
	}
}
