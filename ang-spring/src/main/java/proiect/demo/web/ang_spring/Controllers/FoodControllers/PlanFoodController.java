package proiect.demo.web.ang_spring.Controllers.FoodControllers;

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

import proiect.demo.web.ang_spring.DTO.CreatePlanFoodRequest;
import proiect.demo.web.ang_spring.Entities.Food.PlanFood;
import proiect.demo.web.ang_spring.Services.FoodServices.PlanFoodService;

@RestController
@RequestMapping("/plan-food")
@CrossOrigin(origins = "http://localhost:4200")
public class PlanFoodController {
	
	private final PlanFoodService planFoodServ;

	public PlanFoodController(PlanFoodService planFoodServ) {
		super();
		this.planFoodServ = planFoodServ;
	}
	
	@PostMapping("/{planId}")
	public PlanFood createPlanFood(@RequestBody CreatePlanFoodRequest dto, @PathVariable Long planId, Authentication auth) {
		return planFoodServ.createPlanFood(dto, planId, auth);
	}
	
	@GetMapping("/{planId}")
	public List<PlanFood> getPlanFoods(@PathVariable Long planId, Authentication auth) {
		return planFoodServ.getPlanFoods(planId, auth);
	}
	
	@DeleteMapping("/{planId}")
	public void deletePlanFood(@PathVariable Long planId, Authentication auth) {
		planFoodServ.deletePlanFood(planId, auth);
	}

}
