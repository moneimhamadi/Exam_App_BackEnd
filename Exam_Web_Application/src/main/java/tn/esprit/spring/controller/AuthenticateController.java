package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.config.JwtUtils;
import tn.esprit.spring.entities.JwtRequest;
import tn.esprit.spring.entities.JwtResponse;
import tn.esprit.spring.services.UserDetailsServiceImpl;

@RestController
@CrossOrigin
public class AuthenticateController {

	@Autowired 
	private AuthenticationManager authenticationManager; 
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtUtils jwtUtil;
	
	
	//generate Token
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			throw new Exception("User Not Found");
		}
		///Authetictae
		UserDetails userDetails =this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token =this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		} catch (DisabledException e) {
			throw new Exception("User Diasbaled"+e.getMessage());
		}
		catch (BadCredentialsException e){
			throw new Exception("Invalid Credentiels"+e.getMessage());
		}
	}
	
}
