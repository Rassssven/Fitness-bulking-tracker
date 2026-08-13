package proiect.demo.web.ang_spring.Services.ExerciseServices;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.ExerciseDTOs.SavedExerciseResponseDTO;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;
import proiect.demo.web.ang_spring.Entities.Exercise.SavedExercise;
import proiect.demo.web.ang_spring.Entities.Food.SavedFood;
import proiect.demo.web.ang_spring.db.ExerciseRepository;
import proiect.demo.web.ang_spring.db.UserRepository;
import proiect.demo.web.ang_spring.db.ExerciseRepos.SavedExerciseRepository;

@Service
public class SavedExerciseService {

	private SavedExerciseRepository savedExRepo;
	private UserRepository userRepo;
	private ExerciseRepository exRepo;

	public SavedExerciseService(SavedExerciseRepository savedExRepo, UserRepository userRepo,
			ExerciseRepository exRepo) {
		super();
		this.savedExRepo = savedExRepo;
		this.userRepo = userRepo;
		this.exRepo = exRepo;
	}

	public SavedExercise addExerciseToSaved(Long id, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User invalid!"));
		
		Exercise exer = exRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Exercise invalid!"));
		
		SavedExercise savedEx = new SavedExercise();
		
		savedEx.setUser(user);
		savedEx.setExercise(exer);
		savedEx.setSavedAt(LocalDateTime.now());
		
		return savedExRepo.save(savedEx);
	}
	
	public List<SavedExercise> getSavedExercises(Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Food can't be created!"));
		
		return savedExRepo.findByUser(user);
	}
	
	public void deleteExercise(Long id, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User invalid!"));
		
		SavedExercise exer = savedExRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Exercise invalid!"));
		
		if(!exer.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Access denied!");
		}
		
		savedExRepo.delete(exer);
	}
	
}
