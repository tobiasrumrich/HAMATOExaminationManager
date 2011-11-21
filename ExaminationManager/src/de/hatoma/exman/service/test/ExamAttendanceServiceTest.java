package de.hatoma.exman.service.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.test.BaseTest;

public class ExamAttendanceServiceTest extends BaseTest {
	@Autowired
	private IExamAttendanceService examAttendanceService;

	public IExamAttendanceService getExamAttendanceService() {
		return examAttendanceService;
	}

	public void setExamAttendanceService(
			IExamAttendanceService examAttendanceService) {
		this.examAttendanceService = examAttendanceService;
	}

	@Test
	public void testCalculateAvarageGrade() {
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExamGrade(ExamGrade.G13);

		Assert.assertEquals(ExamGrade.G13,
				examAttendanceService.getCalculatedGrade(examAttendance));
	}

	@Test
	public void testCalculateAvarageGrade2() {
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExamGrade(ExamGrade.G40);

		Assert.assertEquals(ExamGrade.G40,
				examAttendanceService.getCalculatedGrade(examAttendance));
	}

	@Test
	public void testCalculateAvarageGrade3() {
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExamGrade(ExamGrade.G40);
		examAttendance.setSupplementOralExamGrade(OralExamGrade.G40);

		Assert.assertEquals(ExamGrade.G40,
				examAttendanceService.getCalculatedGrade(examAttendance));
	}

	@Test
	public void testCalculateAvarageGrade4() {
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExamGrade(ExamGrade.G40);
		examAttendance.setSupplementOralExamGrade(OralExamGrade.G17);

		Assert.assertEquals(ExamGrade.G40,
				examAttendanceService.getCalculatedGrade(examAttendance));
	}

	@Test
	public void testCalculateAvarageGrade5() {
		ExamAttendance examAttendance = new ExamAttendance();
		examAttendance.setExamGrade(ExamGrade.G50);
		examAttendance.setSupplementOralExamGrade(OralExamGrade.G17);

		// ergibt 3,35 -> G33
		Assert.assertEquals(ExamGrade.G33,
				examAttendanceService.getCalculatedGrade(examAttendance));
	}

}
