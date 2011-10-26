package de.hatoma.exman.service.person;

import java.util.List;

import de.hatoma.exman.model.user.User;

public interface IUserService {

	public User getUserByUsername(String username);
	public User getUserById(long id);
	public List<User> getUserList();
	
	public User create(User newUser);
	public void save(User user);
	public void delete(User user);
	
	
}
