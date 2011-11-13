package de.hatoma.exman.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

public class ManipleDAO extends BaseDAO<Maniple> implements IManipleDAO {

	public ManipleDAO() {
		super(Maniple.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Student> getStudents(long id) {
		Maniple maniple = (Maniple) getHibernateTemplate().load(Maniple.class,
				id);
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(Student.class)
				.add(Restrictions.eq("maniple", maniple));
		return criteria.list();
	}

	// TODO muss weg
	@Deprecated
	public void linkStudent(Student student) {
		Maniple maniple = student.getManiple();
		Collection<Student> students = maniple.getStudent();
		if (students == null) {
			students = new ArrayList<Student>();
		}
		if (!students.contains(student)) {
			students.add(student);
		}
		maniple.setStudent(students);
		update(maniple);
	}

}
