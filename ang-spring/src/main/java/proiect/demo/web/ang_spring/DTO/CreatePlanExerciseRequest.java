package proiect.demo.web.ang_spring.DTO;

import java.time.DayOfWeek;

public class CreatePlanExerciseRequest {

	private String name;
	private String type;
	private int caloriesPerExercise;
	private String description;
	private String muscleGroup;
	private Integer reps;
	private Integer sets;
	private DayOfWeek dayOfWeek;
	
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

	public String getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
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
