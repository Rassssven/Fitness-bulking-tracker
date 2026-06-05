package proiect.demo.web.ang_spring.Services;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.CreatePlanRequest;
import proiect.demo.web.ang_spring.Entities.Goal;
import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.db.GoalRepository;
import proiect.demo.web.ang_spring.db.PlanRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class PlanService {

	private final PlanRepository planRepo;
	private final UserRepository userRepo;
	private final GoalRepository goalRepo;
	
	public PlanService(PlanRepository planRepo, UserRepository userRepo, GoalRepository goalRepo) {
		super();
		this.planRepo = planRepo;
		this.userRepo = userRepo;
		this.goalRepo = goalRepo;
	}

	public Plan createPlan(Plan plan, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		plan.setUser(user);
		
		return planRepo.save(plan);
	}
	
	public Plan createFullPlan(CreatePlanRequest dto, Authentication auth) {
		
		String email = auth.getName();
		
		User current = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Cannot create plan!"));
		
		Goal goal = new Goal();
		
		switch(dto.getType()) {
		    case "bulk":
		        goal.setTargetCalories(3200);
		        break;
	
		    case "cut":
		        goal.setTargetCalories(2200);
		        break;
	
		    case "maintenance":
		        goal.setTargetCalories(2600);
		        break;
		        
		    case "custom":
		        goal.setTargetCalories(2800);
		        break;
		}
		
		goal.setTargetWeight(dto.getTargetWeight());
		goal.setStartDate(LocalDate.now());
		goal.setEndDate(LocalDate.now().plusWeeks(dto.getDuration()));
		
		goal.setUser(current);
		
		goal = goalRepo.save(goal);
		
		Plan plan = new Plan();
		
		plan.setName("My plan");
		plan.setType(dto.getType());
		
		plan.setUser(current);
		plan.setGoal(goal);
		
		return planRepo.save(plan);
	}
	
	public List<Plan> getAllPlans(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		return planRepo.findByUserId(user.getId());
	}
	
	public Plan getPlan(Long id, Authentication auth) throws AccessDeniedException {
		
		String email = auth.getName();

		Plan plan = planRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No plan found!"));
		
		if(!plan.getUser().getEmail().equals(email)) {
			throw new AccessDeniedException("Forbidden");
		}
		
		return plan;
	}
	
	public void deletePlan(Long id, Authentication auth) throws AccessDeniedException {
		
		String email = auth.getName();
		
		Plan plan = planRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No plan found!"));
				
		if(!plan.getUser().getEmail().equals(email)) {
			throw new AccessDeniedException("Forbidden");
		}
		
		planRepo.deleteById(id);
	}
	
	public Plan updatePlan(Long id, Plan updatedPlan, Authentication auth) throws AccessDeniedException {
		
		String email = auth.getName();
		
		Plan plan = planRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("No plan found!"));
		
		if(!plan.getUser().getEmail().equals(email)) {
			throw new AccessDeniedException("Forbidden");
		}
		
		plan.setName(updatedPlan.getName());
		plan.setType(updatedPlan.getType());
		
		return planRepo.save(plan);
		
	}
	
}
