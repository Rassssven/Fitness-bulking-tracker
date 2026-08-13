package proiect.demo.web.ang_spring.Controllers.FoodControllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Entities.Food.SavedFood;
import proiect.demo.web.ang_spring.Services.FoodServices.SavedFoodService;

@RestController
@RequestMapping("/saved-foods")
@CrossOrigin(origins = "http://localhost:4200")
public class SavedFoodController {

	private final SavedFoodService foodServ;

	public SavedFoodController(SavedFoodService foodServ) {
		super();
		this.foodServ = foodServ;
	}
	
	@GetMapping
	public List<SavedFood> getFoodsByUser(Authentication auth) {
		return foodServ.getSavedFoods(auth);
	}
	
	@PostMapping("/{id}")
	public SavedFood createSavedFood(@PathVariable Long id, Authentication auth) {
		return foodServ.addFoodToSaved(id, auth);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSavedFood(@PathVariable Long id, Authentication auth) {
		foodServ.deleteSavedFood(id, auth);
	}
	
}
