package proiect.demo.web.ang_spring.db.ExerciseRepos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Exercise.SavedExercise;
import proiect.demo.web.ang_spring.Entities.Food.SavedFood;

@Repository
public interface SavedExerciseRepository extends JpaRepository<SavedExercise, Long> {

	List<SavedExercise> findByUser(User user);
	
}
