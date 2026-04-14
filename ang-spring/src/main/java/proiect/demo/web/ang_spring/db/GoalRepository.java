package proiect.demo.web.ang_spring.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

	List<Goal> findByUserId(Long userId);
	
	List<Goal> findByName(String name);
	
	List<Goal> findByType(String type);
	
	List<Goal> existsByName(String name);
	
	List<Goal> countByType(String type);
	
	List<Goal> deleteByType(String type);
	
	List<Goal> findByTargetCaloriesGreaterThan(int number);
	
}
