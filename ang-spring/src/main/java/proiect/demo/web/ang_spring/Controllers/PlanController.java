package proiect.demo.web.ang_spring.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Services.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {

	private final PlanService planServ;

	public PlanController(PlanService planServ) {
		super();
		this.planServ = planServ;
	}
	

	
}
