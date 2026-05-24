package proiect.demo.web.ang_spring.Services;

import org.springframework.stereotype.Service;

import proiect.demo.web.ang_spring.db.PlanRepository;

@Service
public class PlanService {

	private final PlanRepository planRepo;

	public PlanService(PlanRepository planRepo) {
		super();
		this.planRepo = planRepo;
	}
	
	
	
}
