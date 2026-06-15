package proiect.demo.web.ang_spring.Services.ExerciseServices;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.AddCatalogExerciseToPlanRequest;
import proiect.demo.web.ang_spring.DTO.CreateExerciseRequest;
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
public class ExerciseService {

	private final ExerciseRepository exRepo;
	private final UserRepository userRepo;
	private final PlanExerciseRepository planExRepo;
	private final PlanRepository planRepo;

	public ExerciseService(ExerciseRepository exRepo, UserRepository userRepo, PlanExerciseRepository planExRepo,
			PlanRepository planRepo) {
		super();
		this.exRepo = exRepo;
		this.userRepo = userRepo;
		this.planExRepo = planExRepo;
		this.planRepo = planRepo;
	}

	public Exercise createExercise(CreateExerciseRequest dto, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Exercise exer = new Exercise();
	
		exer.setName(dto.getName());
		exer.setDescription(dto.getDescription());
		exer.setMuscleGroup(dto.getMuscleGroup());
		exer.setCaloriesPerExercise(dto.getCaloriesPerExercise());
		exer.setType(dto.getType());
		
		exer.setUser(user);
		
		return exRepo.save(exer);
	}
	
	public List<Exercise> getExercises(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return exRepo.findAll();
	}
	
	public List<Exercise> getExercisesByUser(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return exRepo.findByUser(user);
	}
	
	public Exercise getExerciseById(Long exId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Exercise ex = exRepo.findById(exId)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return ex;
	}
	
	public void deleteExercise(Long exId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Exercise ex = exRepo.findById(exId)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		exRepo.delete(ex);
	}
	
	public PlanExercise saveExerciseInPlan(AddCatalogExerciseToPlanRequest dto, Long planId, Long exId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User invalid!"));
		
		Plan plan = planRepo.findById(planId)
				.orElseThrow(() -> new RuntimeException("Plan invalid!"));
		
		if(!plan.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Invalid");
		}
		
		Exercise ex = exRepo.findById(exId)
				.orElseThrow(() -> new RuntimeException("Exercise invalid!"));
		
		PlanExercise planEx = new PlanExercise();
		
		planEx.setExercise(ex);
		planEx.setPlan(plan);
		
		planEx.setReps(dto.getReps());
		planEx.setSets(dto.getSets());
		
		return planExRepo.save(planEx);
		
	}
	
	
	
}
