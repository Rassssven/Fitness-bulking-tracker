package proiect.demo.web.ang_spring.Services.ExerciseServices;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.CreatePlanExerciseRequest;
import proiect.demo.web.ang_spring.Entities.Plan;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;
import proiect.demo.web.ang_spring.Entities.Exercise.PlanExercise;
import proiect.demo.web.ang_spring.db.ExerciseRepository;
import proiect.demo.web.ang_spring.db.PlanExerciseRepository;
import proiect.demo.web.ang_spring.db.PlanRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class PlanExerciseService {

	private PlanExerciseRepository planExRepo;
	private PlanRepository planRepo;
	private ExerciseRepository exRepo;
	private UserRepository userRepo;
	
	public PlanExerciseService(PlanExerciseRepository planExRepo, PlanRepository planRepo, ExerciseRepository exRepo,
			UserRepository userRepo) {
		super();
		this.planExRepo = planExRepo;
		this.planRepo = planRepo;
		this.exRepo = exRepo;
		this.userRepo = userRepo;
	}
	
	public PlanExercise createPlanExercise(CreatePlanExerciseRequest dto, Long planId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		Plan plan = planRepo.findById(planId)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		if(!plan.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Invalid");
		}
		
		Exercise ex = new Exercise();
		
		ex.setName(dto.getName());
		ex.setType(dto.getType());
		ex.setCaloriesPerExercise(dto.getCaloriesPerExercise());
		ex.setDescription(dto.getDescription());
		ex.setMuscleGroup(dto.getMuscleGroup());
		
		ex.setUser(user);
		
		ex = exRepo.save(ex);
		
		PlanExercise planEx = new PlanExercise();
		
		planEx.setExercise(ex);
		planEx.setPlan(plan);
		
		planEx.setReps(dto.getReps());
		planEx.setSets(dto.getSets());
		
		return planExRepo.save(planEx);
	}
	
	public List<PlanExercise> getPlanExercises(Long planId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		Plan plan = planRepo.findById(planId)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		if(!plan.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Invalid");
		} 
		
		return planExRepo.findByPlanId(planId);
	}
	
	public void deletePlanExercise(Long planExId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		PlanExercise planEx = planExRepo.findById(planExId)
				.orElseThrow(() -> new RuntimeException("Invalid"));
	
		if(!planEx.getPlan().getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Invalid");
		}
		
		planExRepo.delete(planEx);
	}
	
}
