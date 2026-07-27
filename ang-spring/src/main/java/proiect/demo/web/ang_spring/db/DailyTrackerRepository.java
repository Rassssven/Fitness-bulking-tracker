package proiect.demo.web.ang_spring.db;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.DailyTracker;

public interface DailyTrackerRepository
        extends JpaRepository<DailyTracker, Long> {

    Optional<DailyTracker> findByUserIdAndDate(
            Long userId,
            LocalDate date
    );

    Optional<DailyTracker> findByIdAndUserId(
            Long trackerId,
            Long userId
    );

    List<DailyTracker> findByUserIdOrderByDateDesc(
            Long userId
    );
}