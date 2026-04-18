package proiect.demo.web.ang_spring.db;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

	List<Goal> findByUserId(Long userId);
	
	List<Goal> findByName(String name);
	
	List<Goal> findByType(String type);
	
	boolean existsByName(String name);
	
	long countByType(String type);
	
	void deleteByType(String type);
	
	List<Goal> findByTargetCaloriesGreaterThan(int number);
	
	List<Goal> findByTargetCaloriesNot(int calories);
	
	List<Goal> findByNameContaining(String text);
	
	List<Goal> findByNameStartsWith(String text);
	
	List<Goal> findByTypeContaining(String text);
	
	List<Goal> findByStartDateBefore(LocalDate date);
	
	List<Goal> findByStartDateAfter(LocalDate date);
	
	List<Goal> findByStartDateBetween(LocalDate date1, LocalDate date2);
	
	List<Goal> findByEndDateIsNull();
	
}
