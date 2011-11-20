package de.hatoma.exman.service;

public interface ITrainmanService {

	/**
	 * Creates a minimum of entities in the database and additionally some Exams
	 * for every StudyBranch and Maniple
	 * 
	 * @param minStudentsPerManiple
	 *            the minimal number of students that will be created for a
	 *            maniple
	 * @param maxStudentsPerManiple
	 *            the maximal number of students that will be created for a
	 *            maniple
	 */
	void bootAndExams(int minStudentsPerManiple, int maxStudentsPerManiple);

	/**
	 * Creates a minimum of entities in the database
	 * 
	 * @param minStudentsPerManiple
	 *            the minimal number of students that will be created for a
	 *            maniple
	 * @param maxStudentsPerManiple
	 *            the maximal number of students that will be created for a
	 *            maniple
	 */
	void bootStrapper(int minStudentsPerManiple, int maxStudentsPerManiple);

	/**
	 * Creates a minimum of entities in the database, Exams for every
	 * StudyBranch and Maniple and a small number of ExamAttendances
	 * 
	 * @param minStudentsPerManiple
	 *            the minimal number of students that will be created for a
	 *            maniple
	 * @param maxStudentsPerManiple
	 *            the maximal number of students that will be created for a
	 *            maniple
	 */
	void completeInitializatain(int minStudentsPerManiple,
			int maxStudentsPerManiple);

	Boolean doesDatabaseComplyWithRequirements();

}
