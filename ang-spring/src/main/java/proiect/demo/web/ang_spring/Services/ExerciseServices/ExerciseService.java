package proiect.demo.web.ang_spring.Services.ExerciseServices;

import java.util.List;

import org.springframework.security.core.Authentication;

import proiect.demo.web.ang_spring.DTO.CreateExerciseRequest;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;
import proiect.demo.web.ang_spring.db.ExerciseRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

public class ExerciseService {

	private final ExerciseRepository exRepo;
	private final UserRepository userRepo;
	
	public ExerciseService(ExerciseRepository exRepo, UserRepository userRepo) {
		super();
		this.exRepo = exRepo;
		this.userRepo = userRepo;
	}

	public Exercise createExercise(CreateExerciseRequest dto, Long exId, Authentication auth) {
		
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
	
	public void deleteExercise(Long exId, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		Exercise ex = exRepo.findById(exId)
				.orElseThrow(() -> new RuntimeException("Invalid"));
		
		exRepo.delete(ex);
	}
	
	
	
}
