package de.hatoma.exman.service;

import java.util.List;

public interface ITrainmanService {
	/**
	 * Transports data from a SQL dump to the system.
	 * (You have to ask the french guy first before the train arrives to station!)
	 * @param transportStatements
	 */
	public void transportSQLDumpToSystem(List<String> transportStatements) throws Exception;
}
