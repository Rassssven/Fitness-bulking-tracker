package proiect.demo.web.ang_spring.Controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping
	public Plan createPlan(@RequestBody Plan plan,
						   Authentication auth) {
		return planServ.createPlan(plan, auth);
	}
	
	@GetMapping
	public List<Plan> getAllPlans(Authentication auth) {
		return planServ.getAllPlans(auth);
	}
	
	@GetMapping("/{id}")
	public Plan getPlanById(@PathVariable Long id, Authentication auth) throws AccessDeniedException {
		return planServ.getPlan(id, auth);
	}
	
	@DeleteMapping("/{id}")
	public void deletePlan(@PathVariable Long id, Authentication auth) throws AccessDeniedException {
		planServ.deletePlan(id, auth);
	}
	
	@PutMapping("/{id}")
	public Plan updatePlan(@PathVariable Long id, Authentication auth, @RequestBody Plan plan) throws AccessDeniedException {
		return planServ.updatePlan(id, auth, plan);
	}

	
}
