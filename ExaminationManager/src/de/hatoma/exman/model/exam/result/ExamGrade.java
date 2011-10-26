package de.hatoma.exman.model.exam.result;

public enum ExamGrade {
	G10(10),G13(13),G17(17),G20(20),G30(30),G33(33),G37(37),G40(40),G50(50),G60(60);
	private int value;
	ExamGrade(int value) {
		this.value = value;
	}
}
