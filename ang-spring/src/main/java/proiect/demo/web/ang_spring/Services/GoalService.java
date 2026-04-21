package proiect.demo.web.ang_spring.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Goal;
import proiect.demo.web.ang_spring.Entities.User;
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
	
	public List<Goal> getGoalsByUser(Long userId) {
		return goalRepo.findByUserId(userId);
	}
	
	public boolean isGoalAchieved(Long goalId, int currentCalories) {
		Goal goal = getGoalById(goalId);
		
		return currentCalories <= goal.getTargetCalories();
	}
	
	public int calculateRemainingCalories(Long goalId, int currentCalories) {
		Goal goal = getGoalById(goalId);
		
		return goal.getTargetCalories() - currentCalories;
	}
	
	/* Exercises */
	
	//1.. Găsește toate goal-urile cu numele exact dat.
	public List<Goal> findGoalsByName(String name) {
		return goalRepo.findByName(name);
	}
	
	//2. Găsește toate goal-urile de tip
	public List<Goal> findGoalsByType(String type) {
		return goalRepo.findByType(type);
	}
	
	//5. Verifică dacă există un goal cu numele dat.
	public boolean goalExists(String name) {
		return goalRepo.existsByName(name);
	}
	
	//6. Numără câte goal-uri există.
	public long goalsCount() {
		return goalRepo.count();
	}
	
	//7. Numără câte goal-uri sunt de tip dat.
	public long goalsCountByType(String type) {
		return goalRepo.countByType(type);
	}
	
	//8. Șterge un goal după id.
	public void deleteGoalById(Long id) {
		goalRepo.deleteById(id);
	}
	
	//9. Șterge toate goal-urile cu un anumit type.
	public void deleteGoalsByType(String type) {
		goalRepo.deleteByType(type);	
	}
	
	/* Comparatii */
	
	//11. Goal-ui cu targetCalories > 2000
	public List<Goal> getGoalsGreaterThan(int number) {
		return goalRepo.findByTargetCaloriesGreaterThan(number);
	}
	
	//Goal-uri care nu au exact 2000
	public List<Goal> caloriesNot(int calories) {
		return goalRepo.findByTargetCaloriesNot(calories);
	}
	
	/* Text Search */
	
	//17. Goal-uri care contin "cut"
	public List<Goal> findByNameContaining(String text) {
		return goalRepo.findByNameContaining(text);
	}
	
	//18. Goal-uri al caror nume incepe cu "Bul"
	public List<Goal> findByStartingName(String text) {
		return goalRepo.findByNameStartsWith(text);
	}
	
	//20. Goal-uri al caror type contine "LO"
	public List<Goal> findByTypeContaining() {
		return goalRepo.findByTypeContaining("LO");
	}
	
	/* Dates */
	
	//22. Goal-uri incepute de azi
	public List<Goal> findByDateBefore() {
		return goalRepo.findByStartDateBefore(LocalDate.now());
	}
	
	public List<Goal> findByDateAfter() {
		return goalRepo.findByStartDateAfter(LocalDate.now());
	}
	
	public List<Goal> findDateBetween() {
		return goalRepo.findByStartDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
	}
	
	public List<Goal> findDateIsNull() {
		return goalRepo.findByEndDateIsNull();
	}
	
	/* AND / OR */
	
	//29. Goal-uri de tip LOSE ale user-ului 1.
	public List<Goal> findByTypeAndUser(String type, Long userId) {
		return goalRepo.findByTypeAndUserId(type, userId);
	}
	
	//30. Goal-uri cu nume dat și type dat.
	public List<Goal> findByTypeAndName(String type, String name) {
		return goalRepo.findByTypeAndName(type, name);
	}
	
	//31. Goal-uri ale user-ului 2 cu targetCalories > 2000.
	public List<Goal> findByTargetAndUser(int targetCalories, Long userId) {
		return goalRepo.findByTargetCaloriesAndUserId(targetCalories, userId);
	}
	
	/* Relatii */
	
	//34. Goal-uri după obiectul User, nu id.
	public List<Goal> findByUser(User user) {
		return goalRepo.findByUser(user);
	}
	
	//35. Goal-uri după email-ul userului.
	public List<Goal> findByUserEmail(String email) {
		return goalRepo.findByUserEmail(email);
	}
	
	//37. Numără goal-urile unui user
	public long countGoalsByUser(Long userId) {
		return goalRepo.countByUserId(userId);
	}
	
	//38. Verifică dacă user-ul are goal cu numele X.
	public boolean verifyUserGoal(Long userId, String name) {
		return goalRepo.existsByUserIdAndName(userId, name);
	}
	
}
