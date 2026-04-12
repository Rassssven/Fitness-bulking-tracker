package proiect.demo.web.ang_spring.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

	List<Goal> findByUserId(Long userId);
	
}
