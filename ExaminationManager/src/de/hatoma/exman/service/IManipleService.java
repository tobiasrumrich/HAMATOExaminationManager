package de.hatoma.exman.service;

import java.util.Collection;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

public interface IManipleService {
	public Maniple createManiple(StudyBranch studyBranch, int year);

	public Collection<Maniple> getAll();

	public Collection<Student> getStudents(long id);

	public Collection<Maniple> findAll();
	
	public Maniple getById(long id);

	public long getManipleCount();
}
