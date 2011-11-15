package de.hatoma.exman.service;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public interface IStudentService {
	public Student createStudent(String matriculationNumber, String forename,
			String lastname, Maniple maniple);

	public Student getStudent(long id);
}
