package proiect.demo.web.ang_spring.Services.ExerciseServices;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.DTO.ExerciseDTOs.SavedExerciseResponseDTO;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;
import proiect.demo.web.ang_spring.Entities.Exercise.SavedExercise;
import proiect.demo.web.ang_spring.db.ExerciseRepository;
import proiect.demo.web.ang_spring.db.UserRepository;
import proiect.demo.web.ang_spring.db.ExerciseRepos.SavedExerciseRepository;

@Service
public class SavedExerciseService {

	private SavedExerciseRepository exRepo;
	private UserRepository userRepo;
	private ExerciseRepository exerRepo;

	public SavedExerciseService(SavedExerciseRepository exRepo, UserRepository userRepo, ExerciseRepository exerRepo) {
		super();
		this.exRepo = exRepo;
		this.userRepo = userRepo;
		this.exerRepo = exerRepo;
	}

	public SavedExerciseResponseDTO addExerciseToSaved(Long id, Authentication auth) {
		
		String email = auth.getName();
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User invalid!"));
		
		Exercise exer = exerRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Exercise invalid!"));
		
		SavedExercise savedEx = new SavedExercise();
		
		savedEx.setUser(user);
		savedEx.setExercise(exer);
		savedEx.setSavedAt(LocalDateTime.now());
		
		SavedExercise saved = exRepo.save(savedEx);
		
	    return new SavedExerciseResponseDTO(
	            saved.getId(),
	            exer.getId(),
	            exer.getName(),
	            exer.getType(),
	            exer.getCaloriesPerExercise(),
	            exer.getDescription(),
	            exer.getMuscleGroup(),
	            saved.getSavedAt()
	    );
	}
	
}
