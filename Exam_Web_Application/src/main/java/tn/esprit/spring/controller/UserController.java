package tn.esprit.spring.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.UserRole;
import tn.esprit.spring.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	//Creating New User
	
	@PostMapping("/")
	public User createUser(@RequestBody User user){
		Set<UserRole> roles=new HashSet<>();  //List Of roles
		Role role=new Role();
		role.setIdrole(45L);
		role.setRolename("Normal");
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		return this.us.createUser(user, roles);
		
	}
	
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userID){
		this.us.deleteUser(userID);
	}
	
	
	
	

}
