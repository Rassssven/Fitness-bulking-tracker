package proiect.demo.web.ang_spring.db.ExerciseRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proiect.demo.web.ang_spring.Entities.Exercise.SavedExercise;

@Repository
public interface SavedExerciseRepository extends JpaRepository<SavedExercise, Long> {

}
