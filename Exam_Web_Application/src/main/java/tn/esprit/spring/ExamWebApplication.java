package tn.esprit.spring;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.UserRole;
import tn.esprit.spring.services.UserService;

@SpringBootApplication
public class ExamWebApplication  implements CommandLineRunner{

	@Autowired UserService ur;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start Coding");
		User user=new User();
		user.setFirstname("Hammadi");
		user.setLastname("Moneim");
		user.setUsername("BigSmoke");
		user.setPassword("0000");
		user.setEmail("hamm****@Esprit.tn");
		user.setProfile("default.png");
		
		Set<UserRole> userRolesSet=new HashSet<>();
		
		Role role1=new Role();
		role1.setIdrole(44L);
		role1.setRolename("Admin");
		
		UserRole userrole=new UserRole();
		userrole.setRole(role1);
		userrole.setUser(user);
		
		userRolesSet.add(userrole);
		
		User user1=this.ur.createUser(user, userRolesSet);
		System.out.println(user1);
	}

}
