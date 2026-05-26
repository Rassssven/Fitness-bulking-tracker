package proiect.demo.web.ang_spring.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Services.PlanService;

@RestController
@RequestMapping("/plans")
@CrossOrigin(origins = "http://localhost:4200")
public class PlanController {

	private final PlanService planServ;

	public PlanController(PlanService planServ) {
		super();
		this.planServ = planServ;
	}
	
	@PostMapping("/{userId}")
	public Plan createPlan(@RequestBody Plan plan,
						   @PathVariable Long userId) {
		return planServ.createPlan(plan, userId);
	}
	
	@GetMapping("/{userId}")
	public List<Plan> getAllPlans(@PathVariable Long userId) {
		return planServ.getAllPlans(userId);
	}
	
	@DeleteMapping
	public void deletePlan(@PathVariable Long userId) {
		planServ.deletePlan(userId);
	}

	
}
