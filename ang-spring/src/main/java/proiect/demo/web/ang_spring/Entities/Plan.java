package proiect.demo.web.ang_spring.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Plans")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Food> foods;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Exercise> exercises;
	
	@OneToMany(cascade = CascadeType.ALL,
		       orphanRemoval = true)
	private List<Workout> workouts;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "goal_id")
	private Goal goal;
	
}
