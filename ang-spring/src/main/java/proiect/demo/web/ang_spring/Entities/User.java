package proiect.demo.web.ang_spring.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import proiect.demo.web.ang_spring.Entities.Enums.Role;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.Entities.Workout.Workout;

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
	private String sex;
	private double height;
	private String activityLevel;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String firstName;
	private String tel;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(unique = true, nullable = false)
	@Email
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Workout> workouts;

	@JsonIgnore
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Food> foods;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Goal> goals;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Exercise> exercises;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",
			   cascade = CascadeType.ALL,
			   orphanRemoval = true)
	private List<Plan> plans;

	@JsonIgnore
	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<DailyTracker> dailyTrackers;

	public User() {	}
	
	public User(String password, Integer age, String sex, double height, String activityLevel, String lastName, String firstName, String tel, String email) {
		super();
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.height = height;
		this.activityLevel = activityLevel;
		this.lastName = lastName;
		this.firstName = firstName;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public List<DailyTracker> getDailyTrackers() {
		return dailyTrackers;
	}

	public void setDailyTrackers(
			List<DailyTracker> dailyTrackers) {

		this.dailyTrackers = dailyTrackers;
	}

	
	
}
