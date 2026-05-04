package proiect.demo.web.ang_spring.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;
	
	public CustomUserDetailsService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		
		return null;
		
	}
	
}
