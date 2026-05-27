package proiect.demo.web.ang_spring.Services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.db.PlanRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class PlanService {

	private final PlanRepository planRepo;
	private final UserRepository userRepo;

	public PlanService(PlanRepository planRepo, UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
		this.planRepo = planRepo;
	}
	
	public Plan createPlan(Plan plan, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		plan.setUser(user);
		
		return planRepo.save(plan);
	}
	
	public List<Plan> getAllPlans(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		return planRepo.findByUserId(user.getId());
	}
	
	public void deletePlan(Long userId) {
		planRepo.deleteById(userId);
	}
	
}
