package de.hatoma.exman.dao;

import de.hatoma.exman.dao.IManipleDAO;
import de.hatoma.exman.model.Student;

public class StudentDAO extends BaseDAO<Student> implements IStudentDAO {

	private IManipleDAO manipleDAO;
	public StudentDAO() {
		super(Student.class);
	}
	
	@Override
	public Student save(Student student) {
		getHibernateTemplate().save(student);
		
		manipleDAO.linkStudent(student);
		return student;
	}

	public IManipleDAO getManipleDAO() {
		return manipleDAO;
	}

	public void setManipleDAO(IManipleDAO manipleDAO) {
		this.manipleDAO = manipleDAO;
	}

}
