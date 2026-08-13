package proiect.demo.web.ang_spring.Services.FoodServices;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.Entities.Food.SavedFood;
import proiect.demo.web.ang_spring.db.FoodRepository;
import proiect.demo.web.ang_spring.db.UserRepository;
import proiect.demo.web.ang_spring.db.FoodRepos.SavedFoodRepository;

@Service
public class SavedFoodService {

	private SavedFoodRepository savedFoodRepo;
	private UserRepository userRepo;
	private FoodRepository foodRepo;
	
	public SavedFoodService(SavedFoodRepository savedfoodRepo, UserRepository userRepo, FoodRepository foodRepo) {
		super();
		this.savedFoodRepo = savedfoodRepo;
		this.userRepo = userRepo;
		this.foodRepo = foodRepo;
	}

	public SavedFood addFoodToSaved(Long id, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid User"));
		
		Food food = foodRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Invalid Food"));
		
		SavedFood savedF = new SavedFood();
		
		savedF.setSavedAt(LocalDateTime.now());
		savedF.setUser(user);
		savedF.setFood(food);
		
		return savedFoodRepo.save(savedF);
	}
	
	public List<SavedFood> getSavedFoods(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return savedFoodRepo.findByUser(user);
	}
	
	public void deleteSavedFood(Long id, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid User"));
		
		SavedFood food = savedFoodRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Invalid Food"));
		
		if(!food.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Access denied!");
		}
		
		savedFoodRepo.delete(food);
	}
	
	
}
