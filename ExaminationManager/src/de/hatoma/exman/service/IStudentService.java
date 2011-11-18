package de.hatoma.exman.service;

import java.util.List;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public interface IStudentService {
	public Student createStudent(String matriculationNumber, String forename,
			String lastname, Maniple maniple);

	public Student getStudent(long id);
	public Student getValidOralStudent(long id);
	public long getStudentCount();
}
