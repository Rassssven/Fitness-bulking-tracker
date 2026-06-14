package proiect.demo.web.ang_spring.DTO;

public class CreateExerciseRequest {

	private String name;
	private String type;
	
	private int caloriesPerExercise;
	private String description;
	private String muscleGroup;
	
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
	
	
	
}
