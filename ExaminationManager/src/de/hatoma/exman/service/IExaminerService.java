package de.hatoma.exman.service;

import java.util.List;

import de.hatoma.exman.model.Examiner;

public interface IExaminerService {
	/**
	 * Erstellt einen neuen Examiner
	 * @param forename Vorname des Examiners
	 * @param lastname Nachname des Examiners
	 * @return Das neu erzeugte Examiner Objekt
	 */
	public Examiner createExaminer(String forename, String lastname);

	/**
	 * Gibt alle vorhandenen Examiner als Liste zurück
	 * @return
	 */
	public List<Examiner> findAll();

	/**
	 * Gibt alle vorhandenen Examiner als JSON zurück
	 * @return String mit JSON Struktur
	 */
	public String getAllExaminersAsJson();

	public String getAllExamSubjectsJson();

	/**
	 * Gibt die aktuelle Anzahl der in Datenbank vorhandenen Examiner Entitäten zurück
	 * @return Anzahl der Examiner/Prüfer als long Wert
	 */
	public long getExaminerCount();

	/**
	 * Lädt einen Examiner anhand der id aus der Datenbank
	 * @param id des zu ladenden Examiner
	 * @return Examiner
	 */
	public Examiner load(long id);
}
