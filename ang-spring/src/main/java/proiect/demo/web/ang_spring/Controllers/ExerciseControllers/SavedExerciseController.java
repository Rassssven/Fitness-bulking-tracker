package proiect.demo.web.ang_spring.Controllers.ExerciseControllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Services.FoodServices.SavedFoodService;

@RestController
@RequestMapping("/foods")
@CrossOrigin(origins = "http://localhost:4200")
public class SavedExerciseController {

	private final SavedFoodService foodServ;

	public SavedExerciseController(SavedFoodService foodServ) {
		super();
		this.foodServ = foodServ;
	}
	
	
	
}
