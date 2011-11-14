package de.hatoma.exman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamSubject;
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
		List<Exam> winfExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : winfExamSubjects) {
			winfExams.add(getExamService().createExam(
					exSubject,
					new Date(2011, 11, 1),
					winfExaminer.get((int) (Math.random() * (winfExaminer
							.size() - 1)))));
		}

		List<Exam> wingExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : wingExamSubjects) {
			wingExams.add(getExamService().createExam(
					exSubject,
					new Date(2011, 10, 9),
					wingExaminer.get((int) (Math.random() * (wingExaminer
							.size() - 1)))));
		}

		List<Exam> bwlExams = new ArrayList<Exam>();
		for (ExamSubject exSubject : bwlExamSubjects) {
			bwlExams.add(getExamService()
					.createExam(
							exSubject,
							new Date(2011, 3, 2),
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

	private List<String> forenameRepository() {
		ArrayList<String> r = new ArrayList<String>();

		r.add("Dieter");
		r.add("Felix");
		r.add("Marcel");
		r.add("Martina");
		r.add("Jessica");
		r.add("Karin");
		r.add("Vanessa");
		r.add("Philipp");
		r.add("René");
		r.add("Sven");
		r.add("Ralph");
		r.add("Daniel");
		r.add("Ralph");
		r.add("Leonie");
		r.add("Andrea");
		r.add("Thomas");
		r.add("Leon");
		r.add("Tanja");
		r.add("Alexander");
		r.add("Maximilian");
		r.add("Jürgen");
		r.add("Gabriele");
		r.add("Uta");
		r.add("Ute");
		r.add("Annett");
		r.add("Dennis");
		r.add("Gabriele");
		r.add("Sarah");
		r.add("Kevin");
		r.add("Stephan");
		r.add("Alexander");
		r.add("Tanja");
		r.add("Birgit");
		r.add("Julia");
		r.add("Silke");
		r.add("Simone");
		r.add("Martina");
		r.add("Thorsten");
		r.add("Janina");
		r.add("Barbara");
		r.add("Anne");
		r.add("Dirk");
		r.add("Jens");
		r.add("Kerstin");
		r.add("Tanja");
		r.add("Simone");
		r.add("Florian");
		r.add("Angelika");
		r.add("Antje");
		r.add("Johanna");
		r.add("Marco");
		r.add("Jessica");
		r.add("Vanessa");
		r.add("Jonas");
		r.add("Patrick");
		r.add("Susanne");
		r.add("Benjamin");
		r.add("Anja");
		r.add("Stefanie");
		r.add("Patrick");
		r.add("Jana");
		r.add("Birgit");
		r.add("Maik");
		r.add("Susanne");
		r.add("Torsten");
		r.add("Swen");
		r.add("Anja");
		r.add("Jan");
		r.add("Erik");
		r.add("Bernd");
		r.add("Barbara");
		r.add("Max");
		r.add("Daniela");
		r.add("Angelika");
		r.add("Luca");
		r.add("Maximilian");
		r.add("Claudia");
		r.add("Sarah");
		r.add("Tobias");
		r.add("Anna");
		r.add("Jens");
		r.add("Angelika");
		r.add("Jessica");
		r.add("Dominik");
		r.add("Anne");
		r.add("Dirk");
		r.add("Eric");
		r.add("Maria");
		r.add("Christina");
		r.add("Kerstin");
		r.add("Petra");
		r.add("Karin");
		r.add("Alexander");
		r.add("Luca");
		r.add("Nicole");
		r.add("Birgit");
		r.add("Mandy");
		r.add("Anne");
		r.add("Daniela");
		r.add("Marko");
		r.add("Vanessa");
		r.add("Paul");
		r.add("Johanna");
		r.add("Sophie");
		r.add("Sabrina");
		r.add("Katrin");
		r.add("Michael");
		r.add("Luca");
		r.add("Kathrin");
		r.add("René");
		r.add("Uta");
		r.add("Brigitte");
		r.add("Frank");
		r.add("Jessica");
		r.add("Phillipp");
		r.add("Maximilian");
		r.add("Leonie");
		r.add("Kevin");
		r.add("Angelika");
		r.add("Ralph");
		r.add("Anne");
		r.add("Steffen");
		r.add("Christin");
		r.add("Robert");
		r.add("Janina");
		r.add("Lucas");
		r.add("Ursula");
		r.add("Nadine");
		r.add("Ulrich");
		r.add("Stefanie");
		r.add("Ulrich");
		r.add("Markus");
		r.add("Paul");
		r.add("Marcel");
		r.add("Heike");
		r.add("Sebastian");
		r.add("Katharina");
		r.add("Sven");
		r.add("Jessika");
		r.add("Brigitte");
		r.add("Anne");
		r.add("Sandra");
		r.add("Max");
		r.add("Lena");
		r.add("Ralph");
		r.add("Annett");
		r.add("Dominik");
		r.add("Manuela");
		r.add("Katja");
		r.add("Jessika");
		r.add("Sabine");
		r.add("Silke");
		r.add("Markus");
		r.add("Johanna");
		r.add("Leah");
		r.add("Peter");
		r.add("Kristin");
		r.add("Sven");
		r.add("Uwe");
		r.add("Franziska");
		r.add("Barbara");
		r.add("Laura");
		r.add("Maria");
		r.add("Max");
		r.add("Jennifer");
		r.add("Marie");
		r.add("Matthias");
		r.add("Katja");
		r.add("Annett");
		r.add("Jana");
		r.add("Gabriele");
		r.add("Max");
		r.add("Daniel");
		r.add("Jürgen");
		r.add("Swen");
		r.add("Swen");
		r.add("Simone");
		r.add("Tanja");
		r.add("René");
		r.add("Antje");
		r.add("Christian");
		r.add("Mandy");
		r.add("Nicole");
		r.add("Sven");
		r.add("Christin");
		r.add("Anke");
		r.add("Erik");
		r.add("Ursula");
		r.add("Swen");
		r.add("Lisa");
		r.add("Jennifer");
		r.add("Luca");
		r.add("Lukas");
		r.add("Christina");
		r.add("Maik");
		r.add("Antje");
		r.add("Andreas");
		r.add("Martina");
		r.add("Alexander");
		r.add("Stefanie");
		r.add("Marcel");
		r.add("Tobias");
		r.add("Hannes");
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
		r.add("Neudorf");
		r.add("Junker");
		r.add("Friedman");
		r.add("Weissmuller");
		r.add("Eggers");
		r.add("Eichel");
		r.add("Peters");
		r.add("Hofmann");
		r.add("Eichelberger");
		r.add("Hirsch");
		r.add("Freud");
		r.add("Waechter");
		r.add("Freitag");
		r.add("Brauer");
		r.add("Eichmann");
		r.add("Bader");
		r.add("Eisenhower");
		r.add("Neustadt");
		r.add("Kaufmann");
		r.add("Fried");
		r.add("Goldschmidt");
		r.add("Wurfel");
		r.add("Faust");
		r.add("Ehrlichmann");
		r.add("Reinhardt");
		r.add("Kuster");
		r.add("Schreiber");
		r.add("Freytag");
		r.add("Traugott");
		r.add("Schweitzer");
		r.add("Metzger");
		r.add("Kuster");
		r.add("Feierabend");
		r.add("Fruehauf");
		r.add("Fuhrmann");
		r.add("Mahler");
		r.add("Ackerman");
		r.add("Amsel");
		r.add("Ackerman");
		r.add("Bachmeier");
		r.add("Schulze");
		r.add("Schuster");
		r.add("Wirtz");
		r.add("Nussbaum");
		r.add("Baumgaertner");
		r.add("Luft");
		r.add("Kappel");
		r.add("Kuefer");
		r.add("Werner");
		r.add("Fisher");
		r.add("Wexler");
		r.add("Meier");
		r.add("Vogt");
		r.add("Krause");
		r.add("Shuster");
		r.add("Barth");
		r.add("Fuchs");
		r.add("Lange");
		r.add("Boehm");
		r.add("Eisenhauer");
		r.add("Gärtner");
		r.add("Moench");
		r.add("Foerster");
		r.add("Beyer");
		r.add("Meister");
		r.add("Lange");
		r.add("Kuhn");
		r.add("Herrmann");
		r.add("Zimmerman");
		r.add("Krause");
		r.add("Wulf");
		r.add("Wurfel");
		r.add("Bieber");
		r.add("Kaufmann");
		r.add("Meyer");
		r.add("Jager");
		r.add("Hoch");
		r.add("Kuester");
		r.add("Holzman");
		r.add("Trommler");
		r.add("Schultheiss");
		r.add("Köhler");
		r.add("Beyer");
		r.add("Pabst");
		r.add("Vogler");
		r.add("Gottschalk");
		r.add("Hahn");
		r.add("Weiss");
		r.add("Bach");
		r.add("Decker");
		r.add("Grunwald");
		r.add("Kuhn");
		r.add("Moeller");
		r.add("Grunwald");
		r.add("Koch");
		r.add("Faust");
		r.add("Ebersbach");
		r.add("Ziegler");
		r.add("Schmid");
		r.add("Koertig");
		r.add("Krüger");
		r.add("Eichel");
		r.add("Mayer");
		r.add("Bader");
		r.add("Ostermann");
		r.add("Egger");
		r.add("Becker");
		r.add("Mauer");
		r.add("Holzman");
		r.add("Baecker");
		r.add("Meier");
		r.add("Müller");
		r.add("Freud");
		r.add("Neudorf");
		r.add("Schmitz");
		r.add("Krueger");
		r.add("Gottlieb");
		r.add("Propst");
		r.add("Freud");
		r.add("Wagner");
		r.add("Shuster");
		r.add("Hofmann");
		r.add("Frankfurter");
		r.add("Finkel");
		r.add("Grunwald");
		r.add("Rothschild");
		r.add("Pfeifer");
		r.add("Neumann");
		r.add("Lemann");
		r.add("Ehrlichmann");
		r.add("Eggers");
		r.add("Dreher");
		r.add("Wulf");
		r.add("Meister");
		r.add("Lang");
		r.add("Fischer");
		r.add("Gerber");
		r.add("Engel");
		r.add("Ebersbach");
		r.add("Kalb");
		r.add("Urner");
		r.add("Faust");
		r.add("Bosch");
		r.add("Koertig");
		r.add("Dresdner");
		r.add("Richter");
		r.add("Schmidt");
		r.add("Weiß");
		r.add("Schiffer");
		r.add("Schreiber");
		r.add("Austerlitz");
		r.add("Eichelberger");
		r.add("Jung");
		r.add("Meier");
		r.add("Eichelberger");
		r.add("Muller");
		r.add("Papst");
		r.add("Feierabend");
		r.add("Theiss");
		r.add("Schwartz");
		r.add("Dietrich");
		r.add("Muench");
		r.add("Reinhardt");
		r.add("Schroder");
		r.add("Schulze");
		r.add("Weber");
		r.add("Baader");
		r.add("Waechter");
		r.add("Fisher");
		r.add("Bürger");
		r.add("Fischer");
		r.add("Schreiner");
		r.add("Achen");
		r.add("Koertig");
		r.add("Mueller");
		r.add("Wolf");
		r.add("Feierabend");
		r.add("Himmel");
		r.add("Freeh");
		r.add("Junker");
		r.add("Schuster");
		r.add("Kirsch");
		r.add("Keller");
		r.add("Walter");
		r.add("Junker");
		r.add("Becker");
		r.add("Faerber");
		r.add("Reinhardt");
		r.add("Freud");
		r.add("Farber");
		r.add("Schmidt");
		r.add("Metzger");
		r.add("Braun");
		r.add("Schmitt");
		r.add("Bachmeier");
		r.add("Fischer");
		r.add("Weiss");
		r.add("Wexler");
		r.add("Ebersbach");
		r.add("Hueber");
		r.add("Schroeter");
		r.add("Rumrich");
		r.add("Lemberg");
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
