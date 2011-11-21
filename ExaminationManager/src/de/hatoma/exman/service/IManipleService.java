package de.hatoma.exman.service;

import java.io.Serializable;
import java.util.Collection;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;

public interface IManipleService extends Serializable{
	public Maniple createManiple(StudyBranch studyBranch, int year);

	public Collection<Maniple> findAll();

	public Collection<Maniple> getAll();

	public String getAllManiplesJson();

	public Maniple getById(long id);

	public long getManipleCount();

	public Collection<Student> getStudents(long id);

	public Maniple load(long id);
}
