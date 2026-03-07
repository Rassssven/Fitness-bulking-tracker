package proiect.demo.web.ang_spring.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import proiect.demo.web.ang_spring.Entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long>{

	@Procedure(procedureName = "add_workout")
	void addWorkout(
			@Param("p_name") String name,
			@Param("p_description") String description
	);
	
	
}
