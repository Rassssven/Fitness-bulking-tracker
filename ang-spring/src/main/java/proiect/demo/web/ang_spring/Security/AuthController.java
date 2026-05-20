package proiect.demo.web.ang_spring.Security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
		
		String response = authService.registerUser(request);
		
		return ResponseEntity.status(201).body(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody LoginRequestDTO request) {
		
		String response = authService.login(request);
		
		return ResponseEntity.ok(response);
	}
	
	
}
