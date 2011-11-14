package de.hatoma.exman.dao;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

@Component
public class ManipleDAO extends BaseDAO<Maniple> implements IManipleDAO {

	public ManipleDAO() {
		super(Maniple.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	// TODO hal: sprechenderer name
	public Collection<Student> getStudents(long id) {
		// TODO hal: laden das Manipels über den DAO erledigen. Dafür ist der ja
		// da :D
		Maniple maniple = (Maniple) getCurrentSession().load(Maniple.class, id);
		Criteria criteria = getCurrentSession().getSessionFactory()
				.getCurrentSession().createCriteria(Student.class)
				.add(Restrictions.eq("maniple", maniple));
		return criteria.list();
	}
}
