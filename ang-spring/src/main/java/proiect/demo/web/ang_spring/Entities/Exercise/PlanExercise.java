package proiect.demo.web.ang_spring.Entities.Exercise;

import jakarta.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;
		
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;

	public PlanExercise() {}
	
	public PlanExercise(Integer reps, Integer sets, Exercise exercise, Plan plan) {
		super();
		this.reps = reps;
		this.sets = sets;
		this.exercise = exercise;
		this.plan = plan;
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


	
}
