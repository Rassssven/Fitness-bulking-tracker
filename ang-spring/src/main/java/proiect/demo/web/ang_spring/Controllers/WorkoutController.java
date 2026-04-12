package proiect.demo.web.ang_spring.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Workout;
import proiect.demo.web.ang_spring.Services.WorkoutService;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

	private final WorkoutService workoutServ;
	
	public WorkoutController(WorkoutService workoutServ) {
		super();
		this.workoutServ = workoutServ;
	}

	@PostMapping
	public Workout createWorkout(@RequestBody Workout workout) {
		return workoutServ.createWorkout(workout);
	}
	
	@GetMapping
	public List<Workout> getWorkouts() {
		return workoutServ.getWorkouts();
	}
	
	@GetMapping("/{id}")
	public Workout getWorkoutById(@PathVariable Long id) {
		return workoutServ.getWorkoutById(id);
	}
	
	@PutMapping("/{id}")
	public Workout updateWorkout(@PathVariable Long id, @RequestBody Workout workout) {
		return workoutServ.updateWorkout(id, workout);
	}
	
	@DeleteMapping("/{id}")
	public void deleteWorkout(@PathVariable Long id) {
		workoutServ.deleteWorkout(id);
	}
	
	
}
