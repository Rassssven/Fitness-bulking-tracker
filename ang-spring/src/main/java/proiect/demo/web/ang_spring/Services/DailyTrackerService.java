package proiect.demo.web.ang_spring.Services;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.db.DailyTrackerRepository;

@Service
public class DailyTrackerService {

	private final DailyTrackerRepository dailyRepo;

	public DailyTrackerService(DailyTrackerRepository dailyRepo) {
		super();
		this.dailyRepo = dailyRepo;
	}
	
	
	
}
