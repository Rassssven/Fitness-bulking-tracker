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

import proiect.demo.web.ang_spring.DTO.CreatePlanExerciseRequest;
import proiect.demo.web.ang_spring.Entities.Exercise.PlanExercise;
import proiect.demo.web.ang_spring.Services.ExerciseServices.PlanExerciseService;

@RestController
@RequestMapping("/plan-exercise")
@CrossOrigin(origins = "http://localhost:4200")
public class PlanExerciseController {

	private final PlanExerciseService planExServ;

	public PlanExerciseController(PlanExerciseService planExServ) {
		super();
		this.planExServ = planExServ;
	}
	
	@PostMapping("/{planId}")
	public PlanExercise createPlanExercise(@RequestBody CreatePlanExerciseRequest dto, @PathVariable Long planId, Authentication auth) {
		return planExServ.createPlanExercise(dto, planId, auth);
	}
	
	@GetMapping("/{planId}")
	public List<PlanExercise> getPlanExercises(@PathVariable Long planId, Authentication auth) {
		return planExServ.getPlanExercises(planId, auth);
	}
	
	@DeleteMapping("/{planExId}")
	public void deletePlanExercise(@PathVariable Long planExId, Authentication auth) {
		planExServ.deletePlanExercise(planExId, auth);
	}
	
	
	
}
