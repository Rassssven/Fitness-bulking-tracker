package proiect.demo.web.ang_spring.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
		Goal created = goalServ.createGoal(goal);
		return ResponseEntity.status(201).body(created);
	}
	
	@GetMapping
	public List<Goal> getAllGoals() {
		return goalServ.getGoals();
	}
	
	@GetMapping("/{id}")
	public Goal getGoalById(@PathVariable Long id) {
		return goalServ.getGoalById(id);
	}
	
	@PutMapping("/{id}")
	public Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
		return goalServ.updateGoal(id, goal);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGoal(@PathVariable Long id) {
		goalServ.deleteGoal(id);
	}
	
	@GetMapping("/user/{userId}")
	public List<Goal> getGoalsByUser(@PathVariable Long id) {
		return goalServ.getGoalsByUser(id);
	}
	
	@GetMapping("/{id}/achieved")
	public boolean isGoalAchieved(@PathVariable Long id,
								  @RequestParam int currentCalories) {
		return goalServ.isGoalAchieved(id, currentCalories);
	}
	
	@GetMapping("/{id}/remaining")
	public int remainingCalories(@PathVariable Long id,
			  					@RequestParam int currentCalories) {
		return goalServ.calculateRemainingCalories(id, currentCalories);
	}
	
}
