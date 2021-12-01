package tn.esprit.spring.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.UserRole;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository ur;
	@Autowired
	private RoleRepository rr;
	
	
	
	@Override
	public User createUser(User user, Set<UserRole> userroles) {
		User local=this.ur.findByUsername(user.getUsername());
		if (local !=null){
			System.out.println("User already Exists  !!!!");	
		}
		else 
		{
			for (UserRole ur:userroles){
				rr.save(ur.getRole());
			}
			user.getUserroles().addAll(userroles);
			local=this.ur.save(user);
		}		
		return local;
	}

}
