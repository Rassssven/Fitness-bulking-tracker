package proiect.demo.web.ang_spring.Entities;

import java.util.List;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String password;
	
	@NotBlank
	private String nume;
	private String prenume;
	private int tel;
	
	@Column(unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user")
	private List<Workout> workouts;

	@OneToMany(mappedBy = "user")
	private List<Food> foods;
	
	@OneToMany(mappedBy = "user")
	private List<Goal> goals;
	
	public User() {
		
	}
	
	public User(String password, String nume, String prenume, int tel, String email) {
		super();
		this.password = password;
		this.nume = nume;
		this.prenume = prenume;
		this.tel = tel;
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNume() {
		return nume;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public int getTel() {
		return tel;
	}
	
	public void setTel(int tel) {
		this.tel = tel;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	
	
	
}
