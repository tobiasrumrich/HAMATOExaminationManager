package de.hatoma.exman.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IExamDao;
import de.hatoma.exman.model.Exam;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.model.ExamType;
import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.service.IExamService;

@Component
public class ExamService implements IExamService {

	@Autowired
	private IExamAttendanceService examAttendanceService;
	@Autowired
	private IExamDao examDao;

	@Override
	public Exam createExam(ExamType examType, ExamSubject examSubject,
			Date date, Examiner examiner) {
		Exam exam = new Exam();
		exam.setExamType(examType);
		exam.setExamSubject(examSubject);
		exam.setDate(date);
		exam.setExaminer(examiner);
		examDao.save(exam);
		return exam;
	}

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	@Override
	public Exam getExamById(long id) {
		return examDao.load(id);
	}

	/**
	 * @return the examDao
	 */
	public IExamDao getExamDao() {
		return examDao;
	}

	@Override
	public List<Exam> getExamList() {
		return examDao.findAll();
	}

	@Override
	public Boolean isExamEditable(Exam exam) {
		List<ExamAttendance> examAttendancesForExam = getExamAttendanceService()
				.getExamAttendancesForExam(exam);

		return (examAttendancesForExam.size() == 0);
	}

	@Override
	public Exam load(Long examId) {
		return examDao.load(examId);
	}

	@Override
	public Serializable save(Exam e) {
		return examDao.save(e);
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	/**
	 * @param examDao
	 *            the examDao to set
	 */
	public void setExamDao(IExamDao examDao) {
		this.examDao = examDao;
	}

	@Override
	public void update(Exam exam) {
		examDao.update(exam);
	}

	public void updateExam(Exam exam) throws Exception {
		examDao.update(exam);
	}

}
