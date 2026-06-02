package proiect.demo.web.ang_spring.Controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Role;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Workout;
import proiect.demo.web.ang_spring.Services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userServ;

	public UserController(UserService userServ) {
		super();
		this.userServ = userServ;
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userServ.createUser(user);
	}
	
	@GetMapping
	public List<User> getUsers() {
		System.out.println("GET USERS HIT");
		
		return userServ.getUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userServ.getUserById(id);
	}
	
	@PutMapping("/me")
	public User updateUser(@RequestBody User user, Authentication auth) {
		return userServ.updateUser(user, auth);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userServ.deleteUser(id);
	}
	
	@PutMapping("/{id}/role")
	public void updateRole(@PathVariable Long id, @RequestParam Role role) {
		userServ.updateRole(id, role);
	}
	
	@GetMapping("/search")
	public List<User> findUsers(@RequestParam String name) {
		return userServ.findUsers(name);
	}
	
}
