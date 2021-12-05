package tn.esprit.spring.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import tn.esprit.spring.services.UserDetailsServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Autowired
	private UserDetailsServiceImpl UserDetailsServiceImpl;
	
	@Autowired
	private JwtUtils JwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String   requestTokenHeader=  request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		
		if( requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")){
			//YES 
			jwtToken= requestTokenHeader.substring(7);
			
			try{
			username=this.JwtUtil.extractUsername(jwtToken);
			}
			catch (ExpiredJwtException e){
				e.printStackTrace();
				System.out.println("Token expired");
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Error");
			}
			}
		else {
			//NO
			System.out.println("Invalid Token ,not start with Bearer String");
		}
		
		
		//Validated
		if (username!= null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
		final UserDetails userDetails=	this.UserDetailsServiceImpl.loadUserByUsername(username);
		
		if (this.JwtUtil.validateToken(jwtToken, userDetails)){
			//Token iS Valid
			UsernamePasswordAuthenticationToken UsernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			UsernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthentication);
		}
		else
		{
			System.out.println("Token Not Valid");
		}
		filterChain.doFilter(request, response);
		}
		
	}
	
	

}
