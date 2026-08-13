package proiect.demo.web.ang_spring.Controllers.ExerciseControllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Exercise.SavedExercise;
import proiect.demo.web.ang_spring.Services.ExerciseServices.SavedExerciseService;
import proiect.demo.web.ang_spring.Services.FoodServices.SavedFoodService;

@RestController
@RequestMapping("/saved-exercises")
@CrossOrigin(origins = "http://localhost:4200")
public class SavedExerciseController {

	private final SavedExerciseService exServ;

	public SavedExerciseController(SavedExerciseService exServ) {
		super();
		this.exServ = exServ;
	}

	@GetMapping
	public List<SavedExercise> getSavedExercises(Authentication auth) {
		return exServ.getSavedExercises(auth);
	}
	
	@PostMapping("/{id}")
	public SavedExercise addExerciseToSaved(@PathVariable Long id, Authentication atuh) {
		return exServ.addExerciseToSaved(id, atuh);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSavedExercise(@PathVariable Long id, Authentication auth) {
		exServ.deleteExercise(id, auth);
	}
	
}
