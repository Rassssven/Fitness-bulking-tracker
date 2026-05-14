package proiect.demo.web.ang_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

	public Exercise() {

	}

	public Exercise(String name, String type, int caloriesPerExercise, String description) {
		super();
		this.name = name;
		this.type = type;
		this.caloriesPerExercise = caloriesPerExercise;
		this.description = description;
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
	
	
	
}
