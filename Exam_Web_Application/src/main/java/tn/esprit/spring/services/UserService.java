package tn.esprit.spring.services;

import java.util.Set;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.UserRole;

public interface UserService {

	public User createUser(User user,Set<UserRole> userroles);
	
}
