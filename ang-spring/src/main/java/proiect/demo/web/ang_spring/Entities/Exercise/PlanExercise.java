package proiect.demo.web.ang_spring.Entities.Exercise;

import java.time.DayOfWeek;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import proiect.demo.web.ang_spring.Entities.Plan;

@Entity
@Table(name = "PlanExercises")
public class PlanExercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer reps;
	private Integer sets;
	
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	
	@ManyToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;
		
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;

	public PlanExercise() {}
	
	public PlanExercise(Integer reps, Integer sets, DayOfWeek dayOfWeek, Exercise exercise, Plan plan) {
		super();
		this.reps = reps;
		this.sets = sets;
		this.exercise = exercise;
		this.plan = plan;
		this.dayOfWeek = dayOfWeek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Integer getReps() {
		return reps;
	}

	public void setReps(Integer reps) {
		this.reps = reps;
	}

	public Integer getSets() {
		return sets;
	}

	public void setSets(Integer sets) {
		this.sets = sets;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	
	
}
