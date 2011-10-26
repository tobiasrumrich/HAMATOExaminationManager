package de.hatoma.exman.model.exam.result;

import java.util.Date;

import de.hatoma.exman.hibernateInternals.Auditable;
import de.hatoma.exman.hibernateInternals.EntityStatus;
import de.hatoma.exman.model.exam.Exam;
import de.hatoma.exman.model.person.Student;
import de.hatoma.exman.model.user.User;

public class ExamResult implements Auditable<ExamResult> {

	private long id;
	private long revision;
	private EntityStatus status = EntityStatus.CURRENT;
	

	private int attempt;
	private Date dateOfExam;
	private ExamGrade examGrade;

	
	private Date supplementalOralExamDate; 
	private ExamGrade supplementOralExamGrade;
	
	private Date lasteChangedOn;
	private User lastChangedBy;
	
	
	
	@Override
	public ExamResult getId() {
		// TODO Auto-generated method stub
		return null;
	}
//private Attempt attempt;
//private Grade 

}
