package de.hatoma.exman.model;

import java.io.Serializable;

public enum ExamGrade implements Serializable {
	G10(10,"1.0"), G13(13,"1.3"), G17(17,"1.7"), G20(20,"2.0"), G30(30,"3.0"), G33(33,"3.3"), G37(37,"3.7"), G40(40,"3.7"), G50(50,"5.0"), G60(60,"6.0");

	private int gradeNum;
	private String gradeString;
	
	ExamGrade(int gradeNum, String gradeString) {
		this.gradeNum = gradeNum;
		this.gradeString = gradeString;
	}
	
	public int getGradeNum() {return gradeNum;};
	public String getGradeString() {return gradeString;};

}
