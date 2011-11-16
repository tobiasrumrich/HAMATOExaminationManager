package de.hatoma.exman.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import de.hatoma.exman.dao.IStudentDao;
import de.hatoma.exman.model.Student;

public class CreateExamAction extends ActionSupport {
	private List<Student> allExaminers;
	@Autowired
	private IStudentDao examinerDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create() {
		allExaminers = new ArrayList<Student>();
		allExaminers.add(examinerDao.load(0));
		return "input";
	}

	public List<Student> getAllExaminers() {
		return allExaminers;
	}

	public void setAllExaminers(List<Student> allExaminers) {
		this.allExaminers = allExaminers;
	}

	public IStudentDao getExaminerDao() {
		return examinerDao;
	}

	public void setStudentDao(IStudentDao examinerDao) {
		this.examinerDao = examinerDao;
	}
}
