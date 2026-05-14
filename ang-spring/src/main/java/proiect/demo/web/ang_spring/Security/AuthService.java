package proiect.demo.web.ang_spring.Security;


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
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	public AuthService(UserRepository userRepo, PasswordEncoder pwdEncoder, JWTService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super();
		this.userRepo = userRepo;
		this.pwdEncoder = pwdEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}
	
	// ------------------------- JWT ----------------------------
	
	public String login(LoginRequestDTO request) {
		
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					request.getEmail(),
					request.getPassword()
			)
		);
		
		UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
		
		return jwtService.generateToken(user);
	}
	
	// ------------------------- Basic Login --------------------
	
	public String registerUser(RegisterRequestDTO request) {
		
		if(userRepo.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already registered!");
			
		} else {
			
			String encodedPassword = pwdEncoder.encode(request.getPassword());
			
			User us = new User();
			
			us.setLastName(request.getNume());
			us.setEmail(request.getEmail());
			us.setPassword(encodedPassword);
			us.setRole("USER");
			
			userRepo.save(us);
			
			return "User saved!";
		}
		
	}
	
	public String loginBasic(LoginRequestDTO request) {
		
		User user = userRepo.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found!"));
			
		boolean isPasswordCorrect = pwdEncoder.matches(
				request.getPassword(),
				user.getPassword()
		);
		
		if(!isPasswordCorrect) {
			throw new RuntimeException("Invalid password!");
		}
				
		return "Login sucssesfull!";
	}
	
}
