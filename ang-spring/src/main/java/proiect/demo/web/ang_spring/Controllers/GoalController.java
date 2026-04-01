package proiect.demo.web.ang_spring.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Goal;
import proiect.demo.web.ang_spring.Services.GoalService;

@RestController
@RequestMapping("/goals")
public class GoalController {

	private final GoalService goalServ;

	public GoalController(GoalService goalServ) {
		super();
		this.goalServ = goalServ;
	}
	
	@PostMapping
	public Goal create(@RequestBody Goal goal) {
		return goalServ.createGoal(goal);
	}
	
}
