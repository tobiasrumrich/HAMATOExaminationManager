package de.hatoma.exman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamAttendanceDao;
import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;

@Component
public class ExamAttendanceService implements IExamAttendanceService {

	@Autowired
	private IExamAttendanceDao examAttendanceDao;
	@Autowired
	private IManipleDao manipleDao;

	@Override
	public ExamAttendance createExamAttendanceForStudent(Student student,
			Exam exam, ExamGrade examGrade) {

		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExam(exam);
		examAttendance.setStudent(student);
		examAttendance.setExamGrade(examGrade);

		examAttendanceDao.save(examAttendance);

		return examAttendance;
	}


	public IExamAttendanceDao getExamAttendanceDao() {
		return examAttendanceDao;
	}


	public void setExamAttendanceDao(IExamAttendanceDao examAttendanceDao) {
		this.examAttendanceDao = examAttendanceDao;
	}


	public IManipleDao getManipleDao() {
		return manipleDao;
	}


	public void setManipleDao(IManipleDao manipleDao) {
		this.manipleDao = manipleDao;
	}


	public List<ExamAttendance> getExamAttendancesForExam(Exam exam) {
		return examAttendanceDao.findByExam(exam);
	}

	@Override
	public List<ExamAttendance> getOralCandidates(long manipleId) {
		Maniple maniple = manipleDao.load(manipleId);
		List<ExamAttendance> allAttendances = examAttendanceDao
				.findbyManipleAndGrade(maniple, ExamGrade.G50);
		ExamSubject currentSubject;
		for (ExamAttendance attendance : allAttendances) {

			currentSubject = attendance.getExam().getExamSubject();
			// TODO Hannes, ich steh grad aufm Schlauch, ich weiß dass es gegen
			// den Nachbarschaftskodex (oder wie dat heißt^^) verstößt aber ich
			// hab grad ne Blockade, wie ichs besser mach

			int numberOfOralExams;
			numberOfOralExams = examAttendanceDao.findByStudentAndExamSubject(
					attendance.getStudent(), currentSubject).size();
			if (numberOfOralExams >= 2) {
				allAttendances.remove(attendance);
			}

			// for every modul in modulliste
			// List list = hole gesamte historie where supplemental oral != 0
			// if list.size >= 2
			// lösche modul from modulliste
			// endif
			// endfor
		}
		return allAttendances;
	}

	@Override
	public void update(ExamAttendance examAttendance) throws Exception {
		examAttendanceDao.update(examAttendance);

	}
}
