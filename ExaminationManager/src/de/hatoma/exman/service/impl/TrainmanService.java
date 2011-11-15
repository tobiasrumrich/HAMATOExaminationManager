package de.hatoma.exman.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.model.StudyBranch;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.service.IManipleService;
import de.hatoma.exman.service.IStudentService;
import de.hatoma.exman.service.IStudyBranchService;
import de.hatoma.exman.service.ITrainmanService;

@Component
public class TrainmanService implements ITrainmanService {

	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExaminerService examinerService;
	@Autowired
	private IExamService examService;
	@Autowired
	private IExamSubjectService examSubjectService;
	@Autowired
	private IManipleService manipleService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IStudyBranchService studyBranchService;

	@Override
	public void createPhaseOne(int minStudentsPerCentury,
			int maxStudentsPerCentury) {
		// Studengänge anlegen
		StudyBranch winfBranch = studyBranchService.createStudyBranch("I",
				"WINF", "BSc. Wirtschaftsinformatik");
		StudyBranch wingBranch = studyBranchService.createStudyBranch("W",
				"WING", "BSc. Wirtschaftsingenieurwesen");
		StudyBranch bwlBranch = studyBranchService.createStudyBranch("B",
				"BWL", "BSc. Betriebswirtschaftslehre");

		// Manipel anlegen 08
		Maniple i08 = getManipleService().createManiple(winfBranch, 2008);
		Maniple w08 = getManipleService().createManiple(wingBranch, 2008);
		Maniple b08 = getManipleService().createManiple(bwlBranch, 2008);

		// Manipel anlegen 09
		Maniple i09 = getManipleService().createManiple(winfBranch, 2009);
		Maniple w09 = getManipleService().createManiple(wingBranch, 2009);
		Maniple b09 = getManipleService().createManiple(bwlBranch, 2009);

		// Manipel anlegen 10
		Maniple i10 = getManipleService().createManiple(winfBranch, 2010);
		Maniple w10 = getManipleService().createManiple(wingBranch, 2010);
		Maniple b10 = getManipleService().createManiple(bwlBranch, 2010);

		// Alle Manipel
		List<Maniple> allManiples = new ArrayList<Maniple>();
		allManiples.add(i08);
		allManiples.add(w08);
		allManiples.add(b08);
		allManiples.add(i09);
		allManiples.add(w09);
		allManiples.add(b09);
		allManiples.add(i10);
		allManiples.add(w10);
		allManiples.add(b10);

		// Alle Winfs
		List<Maniple> allWinfManiples = new ArrayList<Maniple>();
		allWinfManiples.add(i08);
		allWinfManiples.add(i09);
		allWinfManiples.add(i10);

		// Alle Wings
		List<Maniple> allWingManiples = new ArrayList<Maniple>();
		allWingManiples.add(w08);
		allWingManiples.add(w09);
		allWingManiples.add(w10);

		// Alle BWLer
		List<Maniple> allBwlManiples = new ArrayList<Maniple>();
		allBwlManiples.add(b08);
		allBwlManiples.add(b09);
		allBwlManiples.add(b10);

		// Studenten anlegen
		List<String> forenameRepository = forenameRepository();
		List<String> lastnameRepository = lastnameRepository();

		List<Student> listOfStudents = new ArrayList<Student>();

		for (Maniple currentManiple : allManiples) {
			for (int i = 0; i < ((int) (Math.random()
					* (maxStudentsPerCentury - minStudentsPerCentury) + minStudentsPerCentury)); i++) {
				int keyVorname = (int) (Math.random() * (forenameRepository
						.size()));
				int keyNachname = (int) (Math.random() * (lastnameRepository
						.size()));

				listOfStudents.add(studentService.createStudent(
						forenameRepository.get(keyVorname),
						lastnameRepository.get(keyNachname), currentManiple));

			}
		}

		// Prüfer anlegen
		List<Examiner> winfExaminer = new ArrayList<Examiner>();
		winfExaminer.add(getExaminerService().createExaminer("Ralf", "Kesten"));
		winfExaminer.add(getExaminerService().createExaminer("Oliver",
				"Brahmstädt"));
		winfExaminer.add(getExaminerService().createExaminer("Stefan",
				"Reichert"));
		winfExaminer.add(getExaminerService().createExaminer("Hans",
				"Rensmeyer"));
		winfExaminer
				.add(getExaminerService().createExaminer("Fred", "Ludolph"));

		List<Examiner> wingExaminer = new ArrayList<Examiner>();
		wingExaminer.add(getExaminerService().createExaminer("Arno", "Müller"));
		wingExaminer.add(getExaminerService()
				.createExaminer("Volker", "Ahrens"));
		wingExaminer.add(getExaminerService().createExaminer("Bernd",
				"Nörtemann"));
		wingExaminer.add(getExaminerService()
				.createExaminer("Willy", "Netzler"));

		List<Examiner> bwlExaminer = new ArrayList<Examiner>();
		bwlExaminer.add(getExaminerService().createExaminer("Hinrich",
				"Schröder"));
		bwlExaminer
				.add(getExaminerService().createExaminer("Jan", "Bartelsen"));
		bwlExaminer.add(getExaminerService().createExaminer("Lars",
				"Binckebank"));
		bwlExaminer.add(getExaminerService().createExaminer("Michael", "Lühn"));

		// Prüfungsfächer anlegen

		List<ExamSubject> winfExamSubjects = new ArrayList<ExamSubject>();
		for (Maniple iManiple : allWinfManiples) {
			winfExamSubjects.add(getExamSubjectService().createExamSubject(
					"Internet Anwendungsarchitekturen [" + iManiple + "]",
					"Der Grund weshalb es diese Anwendung gibt", "I01",
					iManiple));
			winfExamSubjects.add(getExamSubjectService().createExamSubject(
					"Theoretische Grundlagen der Informatik 2 [" + iManiple
							+ "]", "Grundlagen der Netzwerktechnik", "I02",
					iManiple));
			winfExamSubjects.add(getExamSubjectService().createExamSubject(
					"Allgemeine Betriebswirtschaftslehre [" + iManiple + "]",
					"BWL, i.d.R. mit Fred Ludolph und Börsenspiel", "I03",
					iManiple));
			winfExamSubjects
					.add(getExamSubjectService().createExamSubject(
							"Allgemeine Volkswirtschaftslehre [" + iManiple
									+ "]",
							"Einführung in die Volkswirtschaftslehre", "I04",
							iManiple));
		}

		List<ExamSubject> wingExamSubjects = new ArrayList<ExamSubject>();
		for (Maniple iManiple : allWingManiples) {
			wingExamSubjects.add(getExamSubjectService().createExamSubject(
					"Werkstofftechnik [" + iManiple + "]",
					"Klebstoffe, Metalle und Kunststoffe", "W01", iManiple));
			wingExamSubjects.add(getExamSubjectService().createExamSubject(
					"Messen, Steuern, Regeln [" + iManiple + "]",
					"Laborübungen mit Willy", "W02", iManiple));
			wingExamSubjects.add(getExamSubjectService().createExamSubject(
					"Technische Mathematik 2 [" + iManiple + "]",
					"Berechnung von Brücken", "W03", iManiple));
			wingExamSubjects.add(getExamSubjectService().createExamSubject(
					"Wirtschaftsrecht [" + iManiple + "]", "Recht langweilig",
					"W04", iManiple));
		}

		List<ExamSubject> bwlExamSubjects = new ArrayList<ExamSubject>();
		for (Maniple iManiple : allBwlManiples) {
			bwlExamSubjects.add(getExamSubjectService().createExamSubject(
					"Kosten- und Leistungsrechnung [" + iManiple + "]",
					"Links an Rechts und umgekehrt", "B01", iManiple));
			bwlExamSubjects.add(getExamSubjectService().createExamSubject(
					"Betriebswirtschaftslehre [" + iManiple + "]",
					"ohne Börsenspiel und ohne Ludolph", "B02", iManiple));
			bwlExamSubjects.add(getExamSubjectService()
					.createExamSubject("Marketing [" + iManiple + "]",
							"4p vs 3R", "B03", iManiple));
			bwlExamSubjects.add(getExamSubjectService().createExamSubject(
					"Kommunikationsmanagement [" + iManiple + "]",
					"Redetechniken", "B04", iManiple));

		}

		// Prüfungen anlegen
		List<Integer> hoursRepository = Arrays.asList(9, 10, 11, 12, 13, 14,
				15, 16, 17, 18);
		List<Integer> minutesRepository = Arrays.asList(0, 15, 30, 45);

		List<Exam> winfExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : winfExamSubjects) {
			Calendar calendar = Calendar.getInstance();
			int hour = hoursRepository.get((int) (Math.random() * hoursRepository
					.size()));
			int minute =minutesRepository.get((int) (Math
					.random() * minutesRepository.size()));
			
			int year = exSubject.getManiple().getYear();
			
			int month = (int) (Math.random()*12);
			int day = (int) (Math.random()*28);
			calendar.set(year, month, day,
					hour, minute);

			winfExams.add(getExamService().createExam(
					ExamType.WrittenExam,
					exSubject,
					calendar.getTime(),
					winfExaminer.get((int) (Math.random() * (winfExaminer
							.size() - 1)))));
		}

		List<Exam> wingExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : wingExamSubjects) {
			Calendar calendar = Calendar.getInstance();
			int hour = hoursRepository.get((int) (Math.random() * hoursRepository
					.size()));
			int minute =minutesRepository.get((int) (Math
					.random() * minutesRepository.size()));
			
			int year = exSubject.getManiple().getYear();
			
			int month = (int) (Math.random()*12);
			int day = (int) (Math.random()*28);
			calendar.set(year, month, day,
					hour, minute);

			wingExams.add(getExamService().createExam(
					ExamType.WrittenExam,
					exSubject,
					calendar.getTime(),
					wingExaminer.get((int) (Math.random() * (wingExaminer
							.size() - 1)))));
		}

		List<Exam> bwlExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : bwlExamSubjects) {
			Calendar calendar = Calendar.getInstance();
			int hour = hoursRepository.get((int) (Math.random() * hoursRepository
					.size()));
			int minute =minutesRepository.get((int) (Math
					.random() * minutesRepository.size()));
			
			int year = exSubject.getManiple().getYear();
			
			int month = (int) (Math.random()*12);
			int day = (int) (Math.random()*28);
			calendar.set(year, month, day,
					hour, minute);

			bwlExams.add(getExamService()
					.createExam(
							ExamType.WrittenExam,
							exSubject,
							calendar.getTime(),
							bwlExaminer.get((int) (Math.random() * (bwlExaminer
									.size() - 1)))));
		}

		// Prüfer anlegen -- NEBENAMTLER

		/*
		 * examiner = getExaminerService().createExaminer("Ralf", "Kesten");
		 * ExamSubject examSubject =
		 * getExamSubjectService().createExamSubject("Ausdruckstanz"
		 * ,"A Fancy Description","ADT",maniple);
		 * 
		 * Exam exam = getExamService().createExam(examSubject, new
		 * Date(2011,11,1), examiner);
		 * 
		 * 
		 * ExamGrade examGrade = ExamGrade.G10;
		 * examAttendanceService.createExamAttendanceForStudent(student, exam,
		 * examGrade);
		 */

	}

	/**
	 * Thanks to fakenamegenerator.com
	 * 
	 * @return
	 */
	private List<String> forenameRepository() {
		ArrayList<String> r = new ArrayList<String>();
		r.addAll(
				Arrays.asList("Dieter",
				"Felix", "Marcel", "Martina", "Jessica", "Karin", "Vanessa",
				"Philipp", "René", "Sven", "Ralph", "Daniel", "Ralph",
				"Leonie", "Andrea", "Thomas", "Leon", "Tanja", "Alexander",
				"Maximilian", "Jürgen", "Gabriele", "Uta", "Ute", "Annett",
				"Dennis", "Gabriele", "Sarah", "Kevin", "Stephan", "Alexander",
				"Tanja", "Birgit", "Julia", "Silke", "Simone", "Martina",
				"Thorsten", "Janina", "Barbara", "Anne", "Dirk", "Jens",
				"Kerstin", "Tanja", "Simone", "Florian", "Angelika", "Antje",
				"Johanna", "Marco", "Jessica", "Vanessa", "Jonas", "Patrick",
				"Susanne", "Benjamin", "Anja", "Stefanie", "Patrick", "Jana",
				"Birgit", "Maik", "Susanne", "Torsten", "Swen", "Anja", "Jan",
				"Erik", "Bernd", "Barbara", "Max", "Daniela", "Angelika",
				"Luca", "Maximilian", "Claudia", "Sarah", "Tobias", "Anna",
				"Jens", "Angelika", "Jessica", "Dominik", "Anne", "Dirk",
				"Eric", "Maria", "Christina", "Kerstin", "Petra", "Karin",
				"Alexander", "Luca", "Nicole", "Birgit", "Mandy", "Anne",
				"Daniela", "Marko", "Vanessa", "Paul", "Johanna", "Sophie",
				"Sabrina", "Katrin", "Michael", "Luca", "Kathrin", "René",
				"Uta", "Brigitte", "Frank", "Jessica", "Phillipp",
				"Maximilian", "Leonie", "Kevin", "Angelika", "Ralph", "Anne",
				"Steffen", "Christin", "Robert", "Janina", "Lucas", "Ursula",
				"Nadine", "Ulrich", "Stefanie", "Ulrich", "Markus", "Paul",
				"Marcel", "Heike", "Sebastian", "Katharina", "Sven", "Jessika",
				"Brigitte", "Anne", "Sandra", "Max", "Lena", "Ralph", "Annett",
				"Dominik", "Manuela", "Katja", "Jessika", "Sabine", "Silke",
				"Markus", "Johanna", "Leah", "Peter", "Kristin", "Sven", "Uwe",
				"Franziska", "Barbara", "Laura", "Maria", "Max", "Jennifer",
				"Marie", "Matthias", "Katja", "Annett", "Jana", "Gabriele",
				"Max", "Daniel", "Jürgen", "Swen", "Swen", "Simone", "Tanja",
				"René", "Antje", "Christian", "Mandy", "Nicole", "Sven",
				"Christin", "Anke", "Erik", "Ursula", "Swen", "Lisa",
				"Jennifer", "Luca", "Lukas", "Christina", "Maik", "Antje",
				"Andreas", "Martina", "Alexander", "Stefanie", "Marcel",
				"Tobias", "Hannes"));
		return r;
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public IExaminerService getExaminerService() {
		return examinerService;
	}

	public IExamService getExamService() {
		return examService;
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public IManipleService getManipleService() {
		return manipleService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public IStudyBranchService getStudyBranchService() {
		return studyBranchService;
	}

	private List<String> lastnameRepository() {
		ArrayList<String> r = new ArrayList<String>();
		r.addAll(
		Arrays.asList("Neudorf",
				"Junker", "Friedman", "Weissmuller", "Eggers", "Eichel",
				"Peters", "Hofmann", "Eichelberger", "Hirsch", "Freud",
				"Waechter", "Freitag", "Brauer", "Eichmann", "Bader",
				"Eisenhower", "Neustadt", "Kaufmann", "Fried", "Goldschmidt",
				"Wurfel", "Faust", "Ehrlichmann", "Reinhardt", "Kuster",
				"Schreiber", "Freytag", "Traugott", "Schweitzer", "Metzger",
				"Kuster", "Feierabend", "Fruehauf", "Fuhrmann", "Mahler",
				"Ackerman", "Amsel", "Ackerman", "Bachmeier", "Schulze",
				"Schuster", "Wirtz", "Nussbaum", "Baumgaertner", "Luft",
				"Kappel", "Kuefer", "Werner", "Fisher", "Wexler", "Meier",
				"Vogt", "Krause", "Shuster", "Barth", "Fuchs", "Lange",
				"Boehm", "Eisenhauer", "Gärtner", "Moench", "Foerster",
				"Beyer", "Meister", "Lange", "Kuhn", "Herrmann", "Zimmerman",
				"Krause", "Wulf", "Wurfel", "Bieber", "Kaufmann", "Meyer",
				"Jager", "Hoch", "Kuester", "Holzman", "Trommler",
				"Schultheiss", "Köhler", "Beyer", "Pabst", "Vogler",
				"Gottschalk", "Hahn", "Weiss", "Bach", "Decker", "Grunwald",
				"Kuhn", "Moeller", "Grunwald", "Koch", "Faust", "Ebersbach",
				"Ziegler", "Schmid", "Koertig", "Krüger", "Eichel", "Mayer",
				"Bader", "Ostermann", "Egger", "Becker", "Mauer", "Holzman",
				"Baecker", "Meier", "Müller", "Freud", "Neudorf", "Schmitz",
				"Krueger", "Gottlieb", "Propst", "Freud", "Wagner", "Shuster",
				"Hofmann", "Frankfurter", "Finkel", "Grunwald", "Rothschild",
				"Pfeifer", "Neumann", "Lemann", "Ehrlichmann", "Eggers",
				"Dreher", "Wulf", "Meister", "Lang", "Fischer", "Gerber",
				"Engel", "Ebersbach", "Kalb", "Urner", "Faust", "Bosch",
				"Koertig", "Dresdner", "Richter", "Schmidt", "Weiß",
				"Schiffer", "Schreiber", "Austerlitz", "Eichelberger", "Jung",
				"Meier", "Eichelberger", "Muller", "Papst", "Feierabend",
				"Theiss", "Schwartz", "Dietrich", "Muench", "Reinhardt",
				"Schroder", "Schulze", "Weber", "Baader", "Waechter", "Fisher",
				"Bürger", "Fischer", "Schreiner", "Achen", "Koertig",
				"Mueller", "Wolf", "Feierabend", "Himmel", "Freeh", "Junker",
				"Schuster", "Kirsch", "Keller", "Walter", "Junker", "Becker",
				"Faerber", "Reinhardt", "Freud", "Farber", "Schmidt",
				"Metzger", "Braun", "Schmitt", "Bachmeier", "Fischer", "Weiss",
				"Wexler", "Ebersbach", "Hueber", "Schroeter", "Rumrich",
				"Lemberg"));
		return r;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	public void setExaminerService(IExaminerService examinerService) {
		this.examinerService = examinerService;
	}

	public void setExamService(IExamService examService) {
		this.examService = examService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

	public void setManipleService(IManipleService manipleService) {
		this.manipleService = manipleService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public void setStudyBranchService(IStudyBranchService studyBranchService) {
		this.studyBranchService = studyBranchService;
	}

}
