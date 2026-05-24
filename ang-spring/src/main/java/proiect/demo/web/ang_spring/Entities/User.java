package proiect.demo.web.ang_spring.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	
	//@Column(nullable = false)
	//@Positive
	//@Max(100)
	private Integer age;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String firstName;
	
	private String tel;
	
	@Column(nullable = false)
	private String role;
	
	@Column(unique = true, nullable = false)
	@Email
	private String email;
	
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Workout> workouts;

	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Food> foods;
	
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Goal> goals;
	
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Exercise> exercises;
	
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	private List<Plan> plans;
	
	public User() {
		
	}
	
	public User(String password, String lastName, String firstName, String tel, String role, String email, int age) {
		super();
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.tel = tel;
		this.email = email;
		this.age = age;
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
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	
	
}
