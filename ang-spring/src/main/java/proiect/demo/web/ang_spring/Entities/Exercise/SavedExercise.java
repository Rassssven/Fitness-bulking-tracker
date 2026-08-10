package proiect.demo.web.ang_spring.Entities.Exercise;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import proiect.demo.web.ang_spring.Entities.User;

@Entity
public class SavedExercise {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime savedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "exercise_id")
	@JsonIgnore
	private Exercise exercise;

	public SavedExercise(LocalDateTime savedAt, User user, Exercise exercise) {
		super();
		this.savedAt = savedAt;
		this.user = user;
		this.exercise = exercise;
	}
	
	public SavedExercise() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getSavedAt() {
		return savedAt;
	}

	public void setSavedAt(LocalDateTime savedAt) {
		this.savedAt = savedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	
	
}
