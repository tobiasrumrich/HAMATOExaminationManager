/**
 * 
 */
package de.hatoma.exman.dao.user;

import java.util.List;

import de.hatoma.exman.dao.IDAO;
import de.hatoma.exman.model.user.User;


/**
 * @author tobias
 *
 */
public interface IUserDAO extends IDAO<User>{

	public  User load(long id);

	public List<User> findAll();

	public void delete(User user);

	public User save(User user);

}
