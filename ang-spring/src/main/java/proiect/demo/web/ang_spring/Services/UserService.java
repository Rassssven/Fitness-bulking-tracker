package proiect.demo.web.ang_spring.Services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Role;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	public User getUserById(Long id) {
		return userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found!"));
	}
	
	public User updateUser(User updatedUser, Authentication auth) {
		
		String email = auth.getName();
		
		User current = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
				
		current.setAge(updatedUser.getAge());
		current.setSex(updatedUser.getSex());
		current.setHeight(updatedUser.getHeight());
		current.setActivityLevel(updatedUser.getActivityLevel());
		
		return userRepo.save(current);
	}
	
	public void deleteUser(Long id) {
		User User = getUserById(id);
		userRepo.delete(User);
	}
	
	public User updateRole(Long id, Role role) {
		
		User user = userRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found"));
		
		user.setRole(role);
		
		return userRepo.save(user);
	}
	
	public List<User> findUsers(String name) {
		return userRepo.findByFirstNameContainingIgnoreCase(name);
	}
	
}
