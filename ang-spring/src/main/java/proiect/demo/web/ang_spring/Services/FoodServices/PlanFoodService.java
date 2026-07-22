package proiect.demo.web.ang_spring.Services.FoodServices;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.CreatePlanFoodRequest;
import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.Entities.Food.PlanFood;
import proiect.demo.web.ang_spring.db.FoodRepository;
import proiect.demo.web.ang_spring.db.PlanFoodRepository;
import proiect.demo.web.ang_spring.db.PlanRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class PlanFoodService {

	private PlanFoodRepository planFoodRepo;
	private PlanRepository planRepo;
	private FoodRepository foodRepo;
	private UserRepository userRepo;
	
	public PlanFoodService(PlanFoodRepository planFoodRepo, PlanRepository planRepo, FoodRepository foodRepo,
			UserRepository userRepo) {
		super();
		this.planFoodRepo = planFoodRepo;
		this.planRepo = planRepo;
		this.foodRepo = foodRepo;
		this.userRepo = userRepo;
	}

	public PlanFood createPlanFood(CreatePlanFoodRequest dto, Long planId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		Plan plan = planRepo.findById(planId)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		if(!plan.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Access denied!");
		}
		
		Food food = new Food();
		
		food.setName(dto.getName());
	    food.setCalories(dto.getCalories());
	    food.setProtein(dto.getProtein());
	    food.setCarbs(dto.getCarbs());
	    food.setFat(dto.getFat());
	    food.setDescription(dto.getDescription());
	    
	    food.setUser(user);
	    
	    // Explicit, garantam ca lucram cu entitatea salvata
	    food = foodRepo.save(food);
	    
	    //Setam relatiile deoarece Food nu exista inainte !!
	    
	    DayOfWeek today = LocalDate.now().getDayOfWeek();
	    
	    PlanFood planFood = new PlanFood();
	    
	    planFood.setFood(food);
	    planFood.setPlan(plan);
		
	    planFood.setQuantity(dto.getQuantity());
	    planFood.setMealType(dto.getMealType());
	    planFood.setDayOfWeek(today);

	    return planFoodRepo.save(planFood);
	}
	
	public List<PlanFood> getPlanFoods(Long planId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		Plan plan = planRepo.findById(planId)
				.orElseThrow(() -> new RuntimeException("Plan not found!"));
		
		if(!plan.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Access denied!");
		}
		
		return planFoodRepo.findByPlanId(planId);
	}
	
	public void deletePlanFood(Long planFoodId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		PlanFood planFood = planFoodRepo.findById(planFoodId)
				.orElseThrow(() -> new RuntimeException("Plan food not found!"));
		
		if(!planFood.getPlan().getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Access denied!");
		}
		
		planFoodRepo.delete(planFood);
	}
	
}
