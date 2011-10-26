package de.hatoma.exman.dao.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hatoma.exman.model.user.User;


public class UserDAO extends HibernateDaoSupport implements IUserDAO{

	@Override
	public User save(User user) {
		return (User) getHibernateTemplate().save(user);
	}


	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return getHibernateTemplate().loadAll(User.class);
	}

	@Override
	public User load(long id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}
}
