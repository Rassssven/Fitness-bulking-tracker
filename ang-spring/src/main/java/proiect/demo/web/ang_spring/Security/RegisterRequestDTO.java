package proiect.demo.web.ang_spring.Security;

public class RegisterRequestDTO {

	private String nume;
	private String email;
	private String password;
	
	public RegisterRequestDTO(String nume, String email, String password) {
		super();
		this.nume = nume;
		this.email = email;
		this.password = password;
	}
	
}
