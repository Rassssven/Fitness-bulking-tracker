package proiect.demo.web.ang_spring.SecurityBasic;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Security.JWT.JWTService;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class AuthService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder pwdEncoder;

	public AuthService(UserRepository userRepo, PasswordEncoder pwdEncoder) {
		super();
		this.userRepo = userRepo;
		this.pwdEncoder = pwdEncoder;
	}
	
	public String registerUser(RegisterRequestDTO request) {
		
		if(userRepo.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already registered!");
			
		} else {
			
			String encodedPassword = pwdEncoder.encode(request.getPassword());
			
			User us = new User();
			
			us.setFirstName(request.getFirstName());
			us.setLastName(request.getLastName());
			us.setEmail(request.getEmail());
			us.setPassword(encodedPassword);
			us.setRole("USER");
			
			userRepo.save(us);
			
			return "User saved!";
		}
		
	}
	
}
