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

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
