package proiect.demo.web.ang_spring.Controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
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

import proiect.demo.web.ang_spring.DTO.CreatePlanRequest;
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
	public ResponseEntity<Plan> createPlan(@RequestBody Plan plan, Authentication auth) {
		Plan plaan = planServ.createPlan(plan, auth);

		return ResponseEntity.status(201).body(plaan);
	}
	
	@PostMapping("/create-full-plan")
	public Plan createFullPlan(@RequestBody CreatePlanRequest dto, Authentication auth) {
		return planServ.createFullPlan(dto, auth);
	}
	
	@GetMapping
	public ResponseEntity<List<Plan>> getAllPlans(Authentication auth) {
		return ResponseEntity.ok(planServ.getAllPlans(auth));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Plan> getPlanById(@PathVariable Long id, Authentication auth) throws AccessDeniedException {
		return ResponseEntity.ok(planServ.getPlan(id, auth));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlan(@PathVariable Long id, Authentication auth) throws AccessDeniedException {
		planServ.deletePlan(id, auth);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public Plan updatePlan(@PathVariable Long id, @RequestBody Plan plan, Authentication auth) throws AccessDeniedException {
		return planServ.updatePlan(id, plan, auth);
	}

	
}
