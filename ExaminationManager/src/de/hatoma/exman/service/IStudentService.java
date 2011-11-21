package de.hatoma.exman.service;

import java.io.Serializable;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public interface IStudentService extends Serializable{
	public Student createStudent(String matriculationNumber, String forename,
			String lastname, Maniple maniple);

	/**
	 * Returns a list of all students as JSON
	 * 
	 * @return
	 */
	public String getAllStudentsAsJson();

	public Student getStudent(long id);

	public long getStudentCount();
}
