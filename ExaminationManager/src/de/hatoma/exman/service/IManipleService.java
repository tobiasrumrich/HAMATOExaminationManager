package de.hatoma.exman.service;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.StudyBranch;

public interface IManipleService {
	public Maniple createManiple(StudyBranch studyBranch, int year);
}
