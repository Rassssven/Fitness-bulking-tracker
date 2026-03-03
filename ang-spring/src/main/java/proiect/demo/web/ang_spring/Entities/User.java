package proiect.demo.web.ang_spring.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Column(nullable = false, unique = true)
	private int id;
	
	@Column(nullable = false)
	private String password;
	
	private String nume;
	private String prenume;
	private int tel;
	private String email;
	
	
	
}
