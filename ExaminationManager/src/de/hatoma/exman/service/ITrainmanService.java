package de.hatoma.exman.service;

public interface ITrainmanService {

	/**
	 * Inserts initial data into the database
	 * @param minStudentsPerCentury the minimal number of students to be created for a maniple
	 * @param maxStudentsPerCentury the maximal number of students to be created for a maniple
	 * @param createExamAttendances if set to true, the service will also randomly create exam attendances for students
	 */
	void createPhaseOne(int minStudentsPerCentury, int maxStudentsPerCentury,
			Boolean createExamAttendances);
}
