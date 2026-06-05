package proiect.demo.web.ang_spring.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import proiect.demo.web.ang_spring.Entities.Goal;
import proiect.demo.web.ang_spring.Services.GoalService;

@Tag(name="Goals", description = "Operations related to goals")
@RestController
@RequestMapping("/goals")
public class GoalController {

	private final GoalService goalServ;

	public GoalController(GoalService goalServ) {
		super();
		this.goalServ = goalServ;
	}
	
	@PostMapping
	public ResponseEntity<Goal> createGoal(@RequestBody Goal goal, Authentication auth) {
		Goal created = goalServ.createGoal(goal, auth);
		return ResponseEntity.status(201).body(created);
	}
	
	@Operation(summary = "Get all goals")
	@GetMapping
	public List<Goal> getAllGoals() {
		return goalServ.getGoals();
	}
	
	@Operation(summary = "Get by id", description = "Get a goal by id")
	@GetMapping("/{id}")
	public Goal getGoalById(@Parameter(description = "Goal ID") @PathVariable Long id) {
		return goalServ.getGoalById(id);
	}
	
	@Operation(description = "update a goal")
	@PutMapping("/{id}")
	public Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
		return goalServ.updateGoal(id, goal);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGoal(@PathVariable Long id) {
		goalServ.deleteGoal(id);
	}
	
	@GetMapping("/user/{userId}")
	public List<Goal> getGoalsByUser(@PathVariable Long userId) {
		return goalServ.getGoalsByUser(userId);
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
	
	
	/* Exercises */
	
	@GetMapping("/type/{type}")
	public List<Goal> getGoalsByType(@PathVariable String type) {
		return goalServ.findGoalsByType(type);
	}
	
	@GetMapping("/number")
	public long numberOfGoals() {
		return goalServ.goalsCount();
	}
	
	@GetMapping("/types/{type}")
	public long goalsCountByType(@PathVariable String type) {
		return goalServ.goalsCountByType(type);
	}
	
	@DeleteMapping
	public void deleteGoalById(@PathVariable Long id) {
		goalServ.deleteGoalById(id);
	}
	
	/* Comparatii */
	
	@GetMapping("/greater/{number}")
	public List<Goal> getGoalsGreatherThan(@PathVariable int number) {
		return goalServ.getGoalsGreaterThan(number);
	}
	
	@GetMapping("/not/{calories}")
	public List<Goal> caloriesNot(@RequestParam int calories) {
		return goalServ.caloriesNot(calories);
	}
	
	/* Text Search */
	
	@GetMapping("/type/search")
	public List<Goal> containingType() {
		return goalServ.findByTypeContaining();
	}
	
	/* Dates */
	
	@GetMapping("/today")
	public List<Goal> todayDate() {
		return goalServ.findByDateBefore();
	}
	
	@GetMapping("/tomorrow")
	public List<Goal> todayDateTomorrow() {
		return goalServ.findByDateAfter();
	}
	
	@GetMapping("/between")
	public List<Goal> todayDateBetween() {
		return goalServ.findDateBetween();
	}
	
	@GetMapping("/date/no-end")
	public List<Goal> noEndDate() {
	    return goalServ.findDateIsNull();
	}
	
	/* And / Or */
	
	@GetMapping("/filter")
	public List<Goal> getByTypeAndUser(@RequestParam String type, 
									   @RequestParam Long UserId) {
		return goalServ.findByTypeAndUser(type, UserId);
	}
	
	
	@GetMapping("/filter/types")
	public List<Goal> getByTargetAndUser(@RequestParam int targetCalories,
									   	 @RequestParam Long userId) {
		return goalServ.findByTargetAndUser(targetCalories, userId);
	}
	
	/* Relatii */
	
	
	
	
}
