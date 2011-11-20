package de.hatoma.exman.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import de.hatoma.exman.dao.IManipleDao;
import de.hatoma.exman.model.Maniple;
import de.hatoma.exman.model.Student;

/**
 * 
 * @author Marcel Schroeter, 3690
 * 
 */
@Component
public class ManipleDao extends BaseDao<Maniple> implements IManipleDao {

	public ManipleDao() {
		super(Maniple.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Student> getStudentsForManiple(long id) {
		Maniple maniple = this.load(id);
		Criteria criteria = getCurrentSession().getSessionFactory()
				.getCurrentSession().createCriteria(Student.class)
				.add(Restrictions.eq("maniple", maniple));
		return criteria.list();
	}
}
