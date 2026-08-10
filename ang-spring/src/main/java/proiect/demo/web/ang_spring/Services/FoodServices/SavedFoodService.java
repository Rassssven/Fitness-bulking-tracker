package proiect.demo.web.ang_spring.Services.FoodServices;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.db.FoodRepos.SavedFoodRepository;

@Service
public class SavedFoodService {

	private SavedFoodRepository foodRepo;

	public SavedFoodService(SavedFoodRepository foodRepo) {
		super();
		this.foodRepo = foodRepo;
	}
	
	
	
}
