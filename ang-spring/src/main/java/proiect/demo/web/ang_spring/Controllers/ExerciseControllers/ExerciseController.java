package proiect.demo.web.ang_spring.Controllers.ExerciseControllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.DTO.AddCatalogExerciseToPlanRequest;
import proiect.demo.web.ang_spring.DTO.CreateExerciseRequest;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;
import proiect.demo.web.ang_spring.Entities.Exercise.PlanExercise;
import proiect.demo.web.ang_spring.Services.ExerciseServices.ExerciseService;
import proiect.demo.web.ang_spring.db.UserRepository;

@RestController
@RequestMapping("/exercise")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseController {
	
	private final ExerciseService exServ;
	private final UserRepository userRepo;
	
	public ExerciseController(ExerciseService exServ, UserRepository userRepo) {
		super();
		this.exServ = exServ;
		this.userRepo = userRepo;
	}
	
	@PostMapping
	public Exercise createExercise(@RequestBody CreateExerciseRequest dto, Authentication auth) {
		return this.exServ.createExercise(dto, auth);
	}
	
	@GetMapping
	public List<Exercise> getExercises(Authentication auth) {
		return this.exServ.getExercises(auth);
	}
	
	@DeleteMapping("/{id}")
	public void deleteExercise(@PathVariable Long id, Authentication auth) {
		exServ.deleteExercise(id, auth);
	}
	
	@PostMapping("/{planId}/{exId}")
	public PlanExercise saveExInPlan(@RequestBody AddCatalogExerciseToPlanRequest dto, 
			@PathVariable Long planId, @PathVariable Long exId, Authentication auth) {
		return exServ.saveExerciseInPlan(dto, planId, exId, auth);
	}
	
}
