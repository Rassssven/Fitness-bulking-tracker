package proiect.demo.web.ang_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Food;
import proiect.demo.web.ang_spring.db.FoodRepository;

@Service
public class FoodService {
	
	private final FoodRepository foodRepo;

	public FoodService(FoodRepository foodRepo) {
		super();
		this.foodRepo = foodRepo;
	}
	
	public Food createFood(Food food) {
		return foodRepo.save(food);
	}
	
	public List<Food> getFoods() {
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
