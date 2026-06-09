package proiect.demo.web.ang_spring.Services.FoodServices;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.db.FoodRepository;
import proiect.demo.web.ang_spring.db.PlanRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class FoodService {
	
	private final FoodRepository foodRepo;
	private final UserRepository userRepo;
	private final PlanRepository planRepo;
	
	public FoodService(FoodRepository foodRepo, UserRepository userRepo, PlanRepository planRepo) {
		super();
		this.foodRepo = foodRepo;
		this.userRepo = userRepo;
		this.planRepo = planRepo;
	}

	public Food createFood(Food food, Long planId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Plan plan = planRepo.findById(planId)
				.orElseThrow(() -> new RuntimeException("No plan found!"));
		
		food.setUser(user);
		user.getFoods().add(food);
		
		return foodRepo.save(food);
	}
	
	public List<Food> getFoods(Authentication auth) {
		return foodRepo.findAll();
	}
	
	public Food getFoodById(Long id) {
		return foodRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Food not found!"));
	}
	
	public Food updateFood(Long id, Food updatedFood) {
		Food current = getFoodById(id);
		
		current.setName(updatedFood.getName());
		current.setCalories(updatedFood.getCalories());
		current.setDescription(updatedFood.getDescription());
		
		return foodRepo.save(current);
	}
	
	public void deleteFood(Long id) {
		Food food = getFoodById(id);
		foodRepo.delete(food);
	}
	
	public int getFoodCalories(Long foodId) {
		Food food = getFoodById(foodId);
		return food.getCalories();
	}
	
}
