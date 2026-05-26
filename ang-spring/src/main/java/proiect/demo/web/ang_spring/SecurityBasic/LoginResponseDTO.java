package proiect.demo.web.ang_spring.SecurityBasic;

public class LoginResponseDTO {

	private Long id;
	private String firstName;
	private String email;
	
	public LoginResponseDTO() {
		
	}

	public LoginResponseDTO(Long id, String firstName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
