package proiect.demo.web.ang_spring.SecurityBasic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
        this.authService = authService;
    }
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequestDTO request) {
		return authService.registerUser(request);
	}
	
}
