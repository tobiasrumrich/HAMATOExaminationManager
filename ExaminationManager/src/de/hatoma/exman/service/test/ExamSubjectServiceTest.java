package de.hatoma.exman.service.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.dao.exceptions.InvalidEntityIdException;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamSubject;
import de.hatoma.exman.service.IExamSubjectService;
import de.hatoma.exman.test.BaseTest;

public class ExamSubjectServiceTest extends BaseTest {
	@Autowired
	private IExamSubjectService examSubjectService;

	@Test
	public void testCreateExamSubject() {
		long examSubjectCount = examSubjectService.getExamSubjectCount();
		examSubjectService
				.createExamSubject("a", "b", "c", getDefaultManiple());
		long examSubjectCount1 = examSubjectService.getExamSubjectCount();
		Assert.assertEquals(examSubjectCount + 1, examSubjectCount1);
	}

	@Test
	public void testGetExamSubject() {
		ExamSubject subject = getDefaultExamSubject();
		ExamSubject subject2 = examSubjectService.getExamSubject(subject
				.getId());
		Assert.assertEquals(subject, subject2);
	}

	@Test
	public void testHasStudentPassedSubject() throws InvalidEntityIdException {
		boolean hasStudentPassedSubject = examSubjectService
				.hasStudentPassedSubject(getDefaultStudent(),
						getDefaultExamSubject());
		Assert.assertTrue(hasStudentPassedSubject);

		List<ExamAttendance> examAttendancesForStudentByExamSubject = getExamAttendanceService()
				.getExamAttendancesForStudentByExamSubject(
						getDefaultExamSubject(), getDefaultStudent());
		for (ExamAttendance att : examAttendancesForStudentByExamSubject) {
			getExamAttendanceService().delete(att.getId());
		}

		boolean hasStudentPassedSubject1 = examSubjectService
				.hasStudentPassedSubject(getDefaultStudent(),
						getDefaultExamSubject());
		Assert.assertFalse(hasStudentPassedSubject1);
	}

	public IExamSubjectService getExamSubjectService() {
		return examSubjectService;
	}

	public void setExamSubjectService(IExamSubjectService examSubjectService) {
		this.examSubjectService = examSubjectService;
	}

}
