package proiect.demo.web.ang_spring.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Goal;
import proiect.demo.web.ang_spring.db.GoalRepository;

@Service
public class GoalService {
	
	private final GoalRepository goalRepo;

	public GoalService(GoalRepository goalRepo) {
		super();
		this.goalRepo = goalRepo;
	}
	
	public Goal createGoal(Goal goal) {
		return goalRepo.save(goal);
	}
	
	public List<Goal> getGoals() {
		return goalRepo.findAll();
	}
	
	public Goal getGoalById(Long id) {
		return goalRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Goal not found!"));
	}
	
	public Goal updateGoal(Long id, Goal updatedGoal) {
		
		Goal existing = getGoalById(id);
		
		existing.setName(updatedGoal.getName());
		existing.setTargetCalories(updatedGoal.getTargetCalories());
		existing.setType(updatedGoal.getType());
		
		return goalRepo.save(existing);
		
	}
	
	public void deleteGoal(Long id) {
		Goal goal = getGoalById(id);
		goalRepo.delete(goal);
	}
	
}
