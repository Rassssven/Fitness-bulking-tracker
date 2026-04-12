package proiect.demo.web.ang_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Workout;
import proiect.demo.web.ang_spring.db.WorkoutRepository;

@Service
public class WorkoutService {
	
	private final WorkoutRepository workoutRepo;
	
	public WorkoutService(WorkoutRepository workoutRepo) {
		super();
		this.workoutRepo = workoutRepo;
	}

	public Workout createWorkout(Workout workout) {
		return workoutRepo.save(workout);
	}
	
	public List<Workout> getWorkouts() {
		return workoutRepo.findAll();
	}
	
	public Workout getWorkoutById(Long id) {
		return workoutRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Food not found!"));
	}
	
	public Workout updateWorkout(Long id, Workout updatedWorkout) {
		Workout current = getWorkoutById(id);
		
		current.setName(updatedWorkout.getName());
		current.setBurnedCalories(updatedWorkout.getBurnedCalories());
		current.setDescription(updatedWorkout.getDescription());
		
		return workoutRepo.save(current);
	}
	
	public void deleteWorkout(Long id) {
		Workout workout = getWorkoutById(id);
		workoutRepo.delete(workout);
	}
	
	
}
