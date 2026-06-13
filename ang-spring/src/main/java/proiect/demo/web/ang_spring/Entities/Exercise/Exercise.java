package proiect.demo.web.ang_spring.Entities.Exercise;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Workout.Workout;

@Entity
@Table(name = "Exercises")
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	private int caloriesPerExercise;
	private String description;
	private String muscleGroup;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;
	
	@OneToMany(mappedBy = "exercise")
	@JsonIgnore
	private List<PlanExercise> planExercises;

	public Exercise() {

	}

	public Exercise(String name, String type, int caloriesPerExercise, String description, String muscleGroup) {
		super();
		this.name = name;
		this.type = type;
		this.caloriesPerExercise = caloriesPerExercise;
		this.description = description;
		this.muscleGroup = muscleGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCaloriesPerExercise() {
		return caloriesPerExercise;
	}

	public void setCaloriesPerExercise(int caloriesPerExercise) {
		this.caloriesPerExercise = caloriesPerExercise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public List<PlanExercise> getPlanExercises() {
		return planExercises;
	}

	public void setPlanExercises(List<PlanExercise> planExercises) {
		this.planExercises = planExercises;
	}

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}


}
