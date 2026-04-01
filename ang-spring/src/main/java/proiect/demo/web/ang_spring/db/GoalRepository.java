package proiect.demo.web.ang_spring.db;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
