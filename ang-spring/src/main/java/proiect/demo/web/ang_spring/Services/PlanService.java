package proiect.demo.web.ang_spring.Services;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

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
