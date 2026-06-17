package proiect.demo.web.ang_spring.Services.FoodServices;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.CreateFoodRequest;
import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Enums.FoodStatus;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.db.FoodRepository;
import proiect.demo.web.ang_spring.db.PlanRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class FoodService {
	
	private final FoodRepository foodRepo;
	private final UserRepository userRepo;
	
	public FoodService(FoodRepository foodRepo, UserRepository userRepo) {
		super();
		this.foodRepo = foodRepo;
		this.userRepo = userRepo;
	}

	public Food createFood(CreateFoodRequest dto, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Food food = new Food();
		
		food.setName(dto.getName());
		food.setCalories(dto.getCalories());
		food.setProtein(dto.getProtein());
		food.setCarbs(dto.getCarbs());
		food.setFat(dto.getFat());
		food.setDescription(dto.getDescription());
		food.setStatus(FoodStatus.PRIVATE);
		
		food.setUser(user);
		
		return foodRepo.save(food);
	}
	
	public List<Food> getFoods(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return foodRepo.findAll();
	}
	
	public List<Food> getFoodsByUser(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return foodRepo.findByUser(user);
	}
	
	public Food getFoodById(Long id) {
		return foodRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Food not found!"));
	}
	
	public void deleteFood(Long foodId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Food food = foodRepo.findById(foodId)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		if(!food.getUser().equals(user)) {
			throw new RuntimeException("Invalid user");
		}
		
		foodRepo.delete(food);
	}
		
}