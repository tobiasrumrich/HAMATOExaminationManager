package de.hatoma.exman.service.user;

import de.hatoma.exman.dao.user.IUserDAO;
import de.hatoma.exman.dao.user.UserDAO;
import de.hatoma.exman.model.user.User;

public class UserService implements IUserService {

	
	private IUserDAO userDAO = new UserDAO();;

	public User createUser(String username, String fullname) {

		// Create user object

		User user = new User();
		user.setUsername(username);
		user.setFullname(fullname);

		getUserDAO().save(user);

		return user;
	}

	/**
	 * @return the userDAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO
	 *            the userDAO to set
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
