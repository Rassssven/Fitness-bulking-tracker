package proiect.demo.web.ang_spring.db;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.DailyTracker;

public interface DailyTrackerRepository extends JpaRepository<DailyTracker, Long> {

}
