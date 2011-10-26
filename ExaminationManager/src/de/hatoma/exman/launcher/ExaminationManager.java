package de.hatoma.exman.launcher;

import de.hatoma.exman.model.user.User;
import de.hatoma.exman.service.user.UserService;

public class ExaminationManager {

	
	public static void main (String[] args) {
		UserService service = new UserService();
		
		User user = service.createUser("hmueller", "Hagen Mueller");
		
		System.out.println(user);
	}
}
