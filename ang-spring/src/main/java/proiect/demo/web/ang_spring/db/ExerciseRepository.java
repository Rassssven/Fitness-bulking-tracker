package proiect.demo.web.ang_spring.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Exercise.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

	List<Exercise> findByUser(User user);
	
}
