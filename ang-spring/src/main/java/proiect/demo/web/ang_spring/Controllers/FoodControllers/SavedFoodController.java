package proiect.demo.web.ang_spring.Controllers.FoodControllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Services.FoodServices.SavedFoodService;

@RestController
@RequestMapping("/foods")
@CrossOrigin(origins = "http://localhost:4200")
public class SavedFoodController {

	private final SavedFoodService foodServ;

	public SavedFoodController(SavedFoodService foodServ) {
		super();
		this.foodServ = foodServ;
	}
	
	
	
}
