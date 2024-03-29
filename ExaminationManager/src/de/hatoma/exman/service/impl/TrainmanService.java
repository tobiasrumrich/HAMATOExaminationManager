package de.hatoma.exman.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamGrade;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 7403056340577863909L;
	private List<Maniple> allBwlManiples;
	// All Maniples
	private List<Maniple> allManiples;
	private List<Maniple> allWinfManiples;
	private List<Maniple> allWingManiples;
	private Maniple b08;
	private Maniple b09;
	private Maniple b10;

	private StudyBranch bwlBranch;
	private List<Examiner> bwlExaminer;
	private List<Exam> bwlExams;

	private List<ExamSubject> bwlExamSubjects;
	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExaminerService examinerService;

	@Autowired
	private IExamService examService;
	@Autowired
	private IExamSubjectService examSubjectService;
	// Cohort 08
	private Maniple i08;

	// Cohort 09
	private Maniple i09;
	// Cohort 10
	private Maniple i10;
	private List<Student> listOfStudents;

	@Autowired
	private IManipleService manipleService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IStudyBranchService studyBranchService;
	private Maniple w08;
	private Maniple w09;
	private Maniple w10;
	// StudyBranches
	private StudyBranch winfBranch;
	private List<Examiner> winfExaminer;
	private List<Exam> winfExams;
	private List<ExamSubject> winfExamSubjects;
	private StudyBranch wingBranch;
	private List<Examiner> wingExaminer;
	private List<Exam> wingExams;
	private List<ExamSubject> wingExamSubjects;
	private Random random;

	@Override
	public void bootAndExams(int minStudentsPerManiple,
			int maxStudentsPerManiple) {
		bootStrapper(minStudentsPerManiple, maxStudentsPerManiple);
		createExams();
	}

	public TrainmanService() {
		random = new Random();
	}

	@Override
	public void bootStrapper(int minStudentsPerManiple,
			int maxStudentsPerManiple) {
		createStudyBranches();
		createManiples();
		createStudents(minStudentsPerManiple, maxStudentsPerManiple);
		createExaminers();
		createExamSubjects();

	}

	@Override
	public void completeInitializatain(int minStudentsPerManiple,
			int maxStudentsPerManiple) {
		bootAndExams(minStudentsPerManiple, maxStudentsPerManiple);
		createExamAttendances();
	}

	/**
	 * Private method to create ExamAttendances
	 */
	private void createExamAttendances() {

		List<ExamGrade> grades = Arrays.asList(ExamGrade.G10, ExamGrade.G13,
				ExamGrade.G17, ExamGrade.G20, ExamGrade.G23, ExamGrade.G27,
				ExamGrade.G30, ExamGrade.G33, ExamGrade.G37, ExamGrade.G40,
				ExamGrade.G50, ExamGrade.G60);

		for (Maniple maniple : allWinfManiples) {

			for (Student student : manipleService.getStudents(maniple.getId())) {

				// All students from the current maniple attended to this
				// exam
				ExamGrade grade = grades.get((int) (random.nextFloat() * grades
						.size()));
				examAttendanceService.createExamAttendanceForStudent(student,
						winfExams.get(0), grade);

				// Some people have not attended to this exam. If a person
				// attended will be determined randomly.
				if (random.nextInt(2) == 1) {
					grade = grades.get((int) (random.nextFloat() * grades.size()));
					examAttendanceService.createExamAttendanceForStudent(
							student, winfExams.get(1), grade);
				}

			}
		}

		for (Maniple maniple : allWingManiples) {

			for (Student student : manipleService.getStudents(maniple.getId())) {

				// All students from the current maniple attended to this
				// exam
				ExamGrade grade = grades.get((int) (random.nextFloat() * grades
						.size()));
				examAttendanceService.createExamAttendanceForStudent(student,
						wingExams.get(0), grade);

				// Some people have not attended to this exam. If a person
				// attended will be determined randomly.
				if (random.nextInt(2) == 1) {
					grade = grades.get((int) (random.nextFloat() * grades.size()));
					examAttendanceService.createExamAttendanceForStudent(
							student, wingExams.get(1), grade);
				}

			}
		}

		for (Maniple maniple : allBwlManiples) {

			for (Student student : manipleService.getStudents(maniple.getId())) {

				// All students from the current maniple attended to this
				// exam
				ExamGrade grade = grades.get((int) (random.nextFloat() * grades
						.size()));
				examAttendanceService.createExamAttendanceForStudent(student,
						bwlExams.get(0), grade);

				// Some people have not attended to this exam. If a person
				// attended will be determined randomly.
				if (random.nextInt(2) == 1) {
					grade = grades.get((int) (random.nextFloat() * grades.size()));
					examAttendanceService.createExamAttendanceForStudent(
							student, bwlExams.get(1), grade);
				}

			}
		}

	}

	/**
	 * Private method to create Examiners
	 */
	private void createExaminers() {
		winfExaminer = new ArrayList<Examiner>();
		winfExaminer.add(getExaminerService().createExaminer("Ralf", "Kesten"));
		winfExaminer.add(getExaminerService().createExaminer("Oliver",
				"Brahmstädt"));
		winfExaminer.add(getExaminerService().createExaminer("Stefan",
				"Reichert"));
		winfExaminer.add(getExaminerService().createExaminer("Hans",
				"Rensmeyer"));
		winfExaminer
				.add(getExaminerService().createExaminer("Fred", "Ludolph"));

		wingExaminer = new ArrayList<Examiner>();
		wingExaminer.add(getExaminerService().createExaminer("Arno", "Müller"));
		wingExaminer.add(getExaminerService()
				.createExaminer("Volker", "Ahrens"));
		wingExaminer.add(getExaminerService().createExaminer("Bernd",
				"Nörtemann"));
		wingExaminer.add(getExaminerService()
				.createExaminer("Willy", "Netzler"));

		bwlExaminer = new ArrayList<Examiner>();
		bwlExaminer.add(getExaminerService().createExaminer("Hinrich",
				"Schröder"));
		bwlExaminer
				.add(getExaminerService().createExaminer("Jan", "Bartelsen"));
		bwlExaminer.add(getExaminerService().createExaminer("Lars",
				"Binckebank"));
		bwlExaminer.add(getExaminerService().createExaminer("Michael", "Lühn"));
	}

	/**
	 * Private method to create exams
	 */
	private void createExams() {
		// Create exams
		// As we will randomly create the start time and the date of the exam we
		// first need to create a repository of the allowed values
		List<Integer> hoursRepository = Arrays.asList(9, 10, 11, 12, 13, 14,
				15, 16, 17, 18);
		List<Integer> minutesRepository = Arrays.asList(0, 15, 30, 45);

		winfExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : winfExamSubjects) {
			Calendar calendar = Calendar.getInstance();
			int hour = hoursRepository
					.get((int) (random.nextFloat() * hoursRepository.size()));
			int minute = minutesRepository
					.get((int) (random.nextFloat() * minutesRepository.size()));

			int year = exSubject.getManiple().getYear();

			int month = (int) (random.nextFloat() * 12);
			int day = (int) (random.nextFloat() * 28);
			calendar.set(year, month, day, hour, minute);

			winfExams.add(getExamService().createExam(
					ExamType.WrittenExam,
					exSubject,
					calendar.getTime(),
					winfExaminer.get((int) (random.nextFloat() * (winfExaminer
							.size() - 1)))));
		}

		wingExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : wingExamSubjects) {
			Calendar calendar = Calendar.getInstance();
			int hour = hoursRepository
					.get((int) (random.nextFloat() * hoursRepository.size()));
			int minute = minutesRepository
					.get((int) (random.nextFloat() * minutesRepository.size()));

			int year = exSubject.getManiple().getYear();

			int month = (int) (random.nextFloat() * 12);
			int day = (int) (random.nextFloat() * 28);
			calendar.set(year, month, day, hour, minute);

			wingExams.add(getExamService().createExam(
					ExamType.WrittenExam,
					exSubject,
					calendar.getTime(),
					wingExaminer.get((int) (random.nextFloat() * (wingExaminer
							.size() - 1)))));
		}

		bwlExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : bwlExamSubjects) {
			Calendar calendar = Calendar.getInstance();
			int hour = hoursRepository
					.get((int) (random.nextFloat() * hoursRepository.size()));
			int minute = minutesRepository
					.get((int) (random.nextFloat() * minutesRepository.size()));

			int year = exSubject.getManiple().getYear();

			int month = (int) (random.nextFloat() * 12);
			int day = (int) (random.nextFloat() * 28);
			calendar.set(year, month, day, hour, minute);

			bwlExams.add(getExamService()
					.createExam(
							ExamType.WrittenExam,
							exSubject,
							calendar.getTime(),
							bwlExaminer.get((int) (random.nextFloat() * (bwlExaminer
									.size() - 1)))));
		}
	}

	/**
	 * Private method to create ExamSubjects
	 */
	private void createExamSubjects() {
		winfExamSubjects = new ArrayList<ExamSubject>();
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

		wingExamSubjects = new ArrayList<ExamSubject>();
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

		bwlExamSubjects = new ArrayList<ExamSubject>();
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
	}

	/**
	 * Private method to create Maniples
	 */
	private void createManiples() {
		// Create Maniples using the ManipleService
		i08 = getManipleService().createManiple(winfBranch, 2008);
		w08 = getManipleService().createManiple(wingBranch, 2008);
		b08 = getManipleService().createManiple(bwlBranch, 2008);

		i09 = getManipleService().createManiple(winfBranch, 2009);
		w09 = getManipleService().createManiple(wingBranch, 2009);
		b09 = getManipleService().createManiple(bwlBranch, 2009);

		i10 = getManipleService().createManiple(winfBranch, 2010);
		w10 = getManipleService().createManiple(wingBranch, 2010);
		b10 = getManipleService().createManiple(bwlBranch, 2010);

		// Add them to categorized lists for the purpose of this Service
		allManiples = new ArrayList<Maniple>();
		allManiples.add(i08);
		allManiples.add(w08);
		allManiples.add(b08);
		allManiples.add(i09);
		allManiples.add(w09);
		allManiples.add(b09);
		allManiples.add(i10);
		allManiples.add(w10);
		allManiples.add(b10);

		allWinfManiples = new ArrayList<Maniple>();
		allWinfManiples.add(i08);
		allWinfManiples.add(i09);
		allWinfManiples.add(i10);

		allWingManiples = new ArrayList<Maniple>();
		allWingManiples.add(w08);
		allWingManiples.add(w09);
		allWingManiples.add(w10);

		allBwlManiples = new ArrayList<Maniple>();
		allBwlManiples.add(b08);
		allBwlManiples.add(b09);
		allBwlManiples.add(b10);
	}

	/**
	 * Private method to create Students
	 */
	private void createStudents(int minStudentsPerManiple,
			int maxStudentsPerManiple) {
		// We will create their names randomly by using both a forename and a
		// lastname repository with data from fakenamegenerator.com
		List<String> forenameRepository = forenameRepository();
		List<String> lastnameRepository = lastnameRepository();

		listOfStudents = new ArrayList<Student>();

		// We will start with the matriculation number 1000
		long matriculationNumber = 1000L;

		for (Maniple currentManiple : allManiples) {
			// Determine how many students will be created for the maniple
			for (int i = 0; i < ((int) (random.nextFloat()
					* (maxStudentsPerManiple - minStudentsPerManiple) + minStudentsPerManiple)); i++) {
				int keyVorname = (int) (random.nextFloat() * (forenameRepository
						.size()));
				int keyNachname = (int) (random.nextFloat() * (lastnameRepository
						.size()));

				// Add student to maniple
				listOfStudents.add(studentService.createStudent(
						String.valueOf(matriculationNumber++),
						forenameRepository.get(keyVorname),
						lastnameRepository.get(keyNachname), currentManiple));

			}
		}

	}

	/**
	 * Private method to create StudyBranches
	 */
	private void createStudyBranches() {
		winfBranch = studyBranchService.createStudyBranch("I", "WINF",
				"BSc. Wirtschaftsinformatik");
		wingBranch = studyBranchService.createStudyBranch("W", "WING",
				"BSc. Wirtschaftsingenieurwesen");
		bwlBranch = studyBranchService.createStudyBranch("B", "BWL",
				"BSc. Betriebswirtschaftslehre");
	}

	@Override
	public Boolean doesDatabaseComplyWithRequirements() {
		return (studentService.getStudentCount() > 0
				&& manipleService.getManipleCount() > 0
				&& examSubjectService.getExamSubjectCount() > 0 && examinerService
				.getExaminerCount() > 0);
	}

	/**
	 * Thanks to fakenamegenerator.com The fake forename repository
	 * 
	 * @return
	 */
	private List<String> forenameRepository() {
		ArrayList<String> r = new ArrayList<String>();
		r.addAll(Arrays.asList("Dieter", "Felix", "Marcel", "Martina",
				"Jessica", "Karin", "Vanessa", "Philipp", "René", "Sven",
				"Ralph", "Daniel", "Ralph", "Leonie", "Andrea", "Thomas",
				"Leon", "Tanja", "Alexander", "Maximilian", "Jürgen",
				"Gabriele", "Uta", "Ute", "Annett", "Dennis", "Gabriele",
				"Sarah", "Kevin", "Stephan", "Alexander", "Tanja", "Birgit",
				"Julia", "Silke", "Simone", "Martina", "Thorsten", "Janina",
				"Barbara", "Anne", "Dirk", "Jens", "Kerstin", "Tanja",
				"Simone", "Florian", "Angelika", "Antje", "Johanna", "Marco",
				"Jessica", "Vanessa", "Jonas", "Patrick", "Susanne",
				"Benjamin", "Anja", "Stefanie", "Patrick", "Jana", "Birgit",
				"Maik", "Susanne", "Torsten", "Swen", "Anja", "Jan", "Erik",
				"Bernd", "Barbara", "Max", "Daniela", "Angelika", "Luca",
				"Maximilian", "Claudia", "Sarah", "Tobias", "Anna", "Jens",
				"Angelika", "Jessica", "Dominik", "Anne", "Dirk", "Eric",
				"Maria", "Christina", "Kerstin", "Petra", "Karin", "Alexander",
				"Luca", "Nicole", "Birgit", "Mandy", "Anne", "Daniela",
				"Marko", "Vanessa", "Paul", "Johanna", "Sophie", "Sabrina",
				"Katrin", "Michael", "Luca", "Kathrin", "René", "Uta",
				"Brigitte", "Frank", "Jessica", "Phillipp", "Maximilian",
				"Leonie", "Kevin", "Angelika", "Ralph", "Anne", "Steffen",
				"Christin", "Robert", "Janina", "Lucas", "Ursula", "Nadine",
				"Ulrich", "Stefanie", "Ulrich", "Markus", "Paul", "Marcel",
				"Heike", "Sebastian", "Katharina", "Sven", "Jessika",
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
		r.addAll(Arrays.asList("Neudorf", "Junker", "Friedman", "Weissmuller",
				"Eggers", "Eichel", "Peters", "Hofmann", "Eichelberger",
				"Hirsch", "Freud", "Waechter", "Freitag", "Brauer", "Eichmann",
				"Bader", "Eisenhower", "Neustadt", "Kaufmann", "Fried",
				"Goldschmidt", "Wurfel", "Faust", "Ehrlichmann", "Reinhardt",
				"Kuster", "Schreiber", "Freytag", "Traugott", "Schweitzer",
				"Metzger", "Kuster", "Feierabend", "Fruehauf", "Fuhrmann",
				"Mahler", "Ackerman", "Amsel", "Ackerman", "Bachmeier",
				"Schulze", "Schuster", "Wirtz", "Nussbaum", "Baumgaertner",
				"Luft", "Kappel", "Kuefer", "Werner", "Fisher", "Wexler",
				"Meier", "Vogt", "Krause", "Shuster", "Barth", "Fuchs",
				"Lange", "Boehm", "Eisenhauer", "Gärtner", "Moench",
				"Foerster", "Beyer", "Meister", "Lange", "Kuhn", "Herrmann",
				"Zimmerman", "Krause", "Wulf", "Wurfel", "Bieber", "Kaufmann",
				"Meyer", "Jager", "Hoch", "Kuester", "Holzman", "Trommler",
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
