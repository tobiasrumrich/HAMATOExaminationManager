package de.hatoma.exman.service.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.dao.exceptions.InvalidEntityIdException;
import de.hatoma.exman.model.ExamAttendance;
import de.hatoma.exman.model.ExamGrade;
import de.hatoma.exman.model.OralExamGrade;
import de.hatoma.exman.model.Student;
import de.hatoma.exman.service.IExamAttendanceService;
import de.hatoma.exman.test.BaseTest;

/**
 * @author Hannes Lemberg 3547
 * 
 */
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

	@Test
	public void testAddOralExaminationResultToExamAttendance() {
		ExamAttendance examAttendance = getDefaultExamAttendance();
		Assert.assertNull(examAttendance.getSupplementalOralExamDate());
		Assert.assertNull(examAttendance.getSupplementOralExamGrade());

		try {
			examAttendanceService.addOralExaminationResultToExamAttendance(
					getDefaultExamAttendance(), OralExamGrade.G20,
					getCurrentDate());
		} catch (Throwable e) {
			return;
		}
		Assert.fail("Es hätte eine Exception geworfen werden müssen");
	}

	@Test
	public void testAddOralExaminationResultToExamAttendance1()
			throws Exception {
		ExamAttendance examAttendance = getDefaultExamAttendance();

		examAttendance.setExamGrade(ExamGrade.G50);
		getExamAttendanceDao().update(examAttendance);

		Assert.assertNull(examAttendance.getSupplementalOralExamDate());
		Assert.assertNull(examAttendance.getSupplementOralExamGrade());

		examAttendanceService
				.addOralExaminationResultToExamAttendance(
						getDefaultExamAttendance(), OralExamGrade.G20,
						getCurrentDate());
		ExamAttendance examAttendance1 = getDefaultExamAttendance();
		Assert.assertNotNull(examAttendance1.getSupplementalOralExamDate());
		Assert.assertNotNull(examAttendance1.getSupplementOralExamGrade());
	}

	@Test
	public void testCreateExamAttendanceForStudent() {
		ExamAttendance e = examAttendanceService
				.createExamAttendanceForStudent(getDefaultStudent(),
						getDefaultExam(), ExamGrade.G20);
		ExamAttendance e2 = examAttendanceService.getExamAttendanceById(e
				.getId());
		Assert.assertEquals(ExamGrade.G20, e2.getExamGrade());
	}

	@Test
	public void testDelete() throws InvalidEntityIdException {
		List<ExamAttendance> all1 = getExamAttendanceDao().findAll();
		int size1 = all1.size();

		Assert.assertTrue(size1 >= 1);

		ExamAttendance examAttendance = all1.get(0);
		examAttendanceService.delete(examAttendance.getId());

		List<ExamAttendance> all2 = getExamAttendanceDao().findAll();
		int size2 = all2.size();

		Assert.assertEquals(size1 - 1, size2);
	}

	@Test
	public void testGetAllStudentsEligibleForExamAttendance() {
		int size1 = examAttendanceService
				.getAllStudentsEligibleForExamAttendance(getDefaultExam())
				.size();

		Student student = new Student();
		student.setForename("a");
		student.setLastname("ccc");
		student.setManiple(getDefaultManiple());
		student.setMatriculationNumber("asdf");
		getStudentDao().save(student);

		int size2 = examAttendanceService
				.getAllStudentsEligibleForExamAttendance(getDefaultExam())
				.size();
		Assert.assertTrue(size1 < size2);
	}
}
