package proiect.demo.web.ang_spring.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.Services.DailyTrackerService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class DailyTrackerController {

	private final DailyTrackerService dailyServ;

	public DailyTrackerController(DailyTrackerService dailyServ) {
		super();
		this.dailyServ = dailyServ;
	}

	
}
