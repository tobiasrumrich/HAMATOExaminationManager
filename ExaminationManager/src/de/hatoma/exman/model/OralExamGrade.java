package de.hatoma.exman.model;

import java.io.Serializable;

public enum OralExamGrade implements Serializable {
	G10(10,"1.0"), G13(13,"1.3"), G17(17,"1.7"), G20(20,"2.0"), G23(23,"2.3"), G27(27,"2.7"), G30(30,"3.0"), G33(33,"3.3"), G37(37,"3.7"), G40(40,"4.0"), G50(50,"5.0");

	private int asNumber;
	private String asExpression;
	
	
	OralExamGrade(int gradeNum, String gradeString) {
		this.asNumber = gradeNum;
		this.asExpression = gradeString;
	}
	
	public int getAsNumber() {return asNumber;};
	public String getAsExpression() {return asExpression;};

}
