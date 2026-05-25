package proiect.demo.web.ang_spring.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping
	public Plan createPlan(@RequestBody Plan plan) {
		return planServ.createPlan(plan);
	}

	
}
