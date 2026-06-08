package proiect.demo.web.ang_spring.Controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Food;
import proiect.demo.web.ang_spring.Services.FoodService;

@RestController
@RequestMapping("/foods")
public class FoodController {

	private final FoodService foodServ;
	
	public FoodController(FoodService foodServ) {
		super();
		this.foodServ = foodServ;
	}
	
	@PostMapping
	//@PreAuthorize("hasRole('USER')")
	public Food createFood(@RequestBody Food food,
						   Authentication auth) {
		return foodServ.createFood(food, auth);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public List<Food> getFoods(Authentication auth) {
		return foodServ.getFoods(auth);
	}
	
	@GetMapping("/{id}")
	public Food getFoodById(@PathVariable Long id) {
		return foodServ.getFoodById(id);
	}
	
	@PutMapping("/{id}")
	public Food updateFood(@PathVariable Long id, @RequestBody Food food) {
		return foodServ.updateFood(id, food);
	}
	
	@DeleteMapping("/{id}")
	public void deleteFood(@PathVariable Long id) {
		foodServ.deleteFood(id);
	}
	
	@GetMapping("/calories/{id}")
	public int getCalories(@PathVariable Long id) {
		return foodServ.getFoodCalories(id);
	}
	
}
