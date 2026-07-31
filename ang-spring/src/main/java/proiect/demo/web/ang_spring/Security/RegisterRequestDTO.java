package proiect.demo.web.ang_spring.Security;

public class RegisterRequestDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Double height;
	
	public RegisterRequestDTO() {
		
	}
	
	public RegisterRequestDTO(String firstName, String lastName, String email, String password, Double height) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.height = height;
	}
	
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
