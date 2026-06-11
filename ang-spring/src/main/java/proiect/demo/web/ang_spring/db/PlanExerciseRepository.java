package proiect.demo.web.ang_spring.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Exercise.PlanExercise;

public interface PlanExerciseRepository extends JpaRepository<PlanExercise, Long> {

	List<PlanExercise> findByPlanId(Long planId);
	
}
