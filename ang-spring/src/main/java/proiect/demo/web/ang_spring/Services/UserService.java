package proiect.demo.web.ang_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
	public User updateUser(Long id, User updatedUser) {
		User current = getUserById(id);
		
		current.setNume(updatedUser.getNume());
		current.setPrenume(updatedUser.getPrenume());
		current.setEmail(updatedUser.getEmail());
		current.setTel(updatedUser.getTel());
		
		return userRepo.save(current);
	}
	
	public void deleteUser(Long id) {
		User User = getUserById(id);
		userRepo.delete(User);
	}
	
}
