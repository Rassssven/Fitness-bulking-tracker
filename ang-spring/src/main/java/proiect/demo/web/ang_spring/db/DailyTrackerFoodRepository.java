package proiect.demo.web.ang_spring.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proiect.demo.web.ang_spring.Entities.Food.DailyTrackerFood;

public interface DailyTrackerFoodRepository
        extends JpaRepository<DailyTrackerFood, Long> {

    List<DailyTrackerFood> findByDailyTrackerIdOrderByMealNumberAsc(
            Long trackerId
    );

    Optional<DailyTrackerFood> findByIdAndDailyTrackerUserId(
            Long dailyTrackerFoodId,
            Long userId
    );
}