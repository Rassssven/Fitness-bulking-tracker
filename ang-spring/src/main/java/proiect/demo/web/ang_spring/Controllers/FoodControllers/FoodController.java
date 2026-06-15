package proiect.demo.web.ang_spring.Controllers.FoodControllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.DTO.CreateFoodRequest;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.Services.FoodServices.FoodService;

@RestController
@RequestMapping("/foods")
@CrossOrigin(origins = "http://localhost:4200")
public class FoodController {

	private final FoodService foodServ;
	
	public FoodController(FoodService foodServ) {
		super();
		this.foodServ = foodServ;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public Food createFood(@RequestBody CreateFoodRequest dto, Authentication auth) {
		return foodServ.createFood(dto, auth);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public List<Food> getFoods(Authentication auth) {
		return foodServ.getFoods(auth);
	}
	
	@GetMapping("/user")
	public List<Food> getFoodsByUser(Authentication auth) {
		return foodServ.getFoodsByUser(auth);
	}
	
	@GetMapping("/{id}")
	public Food getFoodById(@PathVariable Long id) {
		return foodServ.getFoodById(id);
	}
	
	@DeleteMapping
	public void deleteFood(@PathVariable Long foodId, Authentication auth) {
		foodServ.deleteFood(foodId, auth);
	}

	
}
