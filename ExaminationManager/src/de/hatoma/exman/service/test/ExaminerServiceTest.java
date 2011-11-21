package de.hatoma.exman.service.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hatoma.exman.model.Examiner;
import de.hatoma.exman.service.IExaminerService;
import de.hatoma.exman.test.BaseTest;

public class ExaminerServiceTest extends BaseTest {
	@Autowired
	private IExaminerService examinerService;

	public IExaminerService getExaminerService() {
		return examinerService;
	}

	public void setExaminerService(IExaminerService examinerService) {
		this.examinerService = examinerService;
	}

	@Test
	public void testCreateExaminer() {
		List<Examiner> findAll = examinerService.findAll();
		int size = findAll == null ? 0 : findAll.size();
		examinerService.createExaminer("Xaaa", "Bbbbb");
		int size1 = examinerService.findAll().size();
		Assert.assertEquals(size + 1, size1);
	}

	@Test
	public void testFindAll() {

		examinerService.createExaminer("Xaaa", "Bbbbb");
		List<Examiner> findAll = examinerService.findAll();
		for (Examiner e : findAll) {
			if (e.getForename() == "Xaaa" && e.getLastname() == "Bbbbb") {
				return;
			}
		}
		Assert.fail("Nicht gefunden");
	}

	@Test
	public void testGetExaminerCount() {
		long size = examinerService.getExaminerCount();
		examinerService.createExaminer("Xaaa", "Bbbbb");
		long size1 = examinerService.getExaminerCount();
		Assert.assertEquals(size + 1, size1);
	}

}
