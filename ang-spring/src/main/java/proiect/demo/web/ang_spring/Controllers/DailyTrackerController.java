package proiect.demo.web.ang_spring.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proiect.demo.web.ang_spring.DTO.AddDailyFoodRequest;
import proiect.demo.web.ang_spring.DTO.DailyTrackerResponse;
import proiect.demo.web.ang_spring.Services.DailyTrackerService;

@RestController
@RequestMapping("/daily-tracker")
@CrossOrigin(origins = "http://localhost:4200")
public class DailyTrackerController {

	private final DailyTrackerService dailyTrackerServ;

	public DailyTrackerController(
			DailyTrackerService dailyTrackerServ) {

		this.dailyTrackerServ = dailyTrackerServ;
	}

	/*
	 * Returnează trackerul zilei curente.
	 * Dacă nu există, service-ul îl creează automat.
	 */
	@GetMapping("/today")
	public DailyTrackerResponse getTodayTracker(
			Authentication auth) {

		return dailyTrackerServ.getTodayTracker(auth);
	}

	/*
	 * Adaugă o masă în trackerul zilei curente.
	 */
	@PostMapping("/today/foods")
	public DailyTrackerResponse addFoodToToday(
			@RequestBody AddDailyFoodRequest dto,
			Authentication auth) {

		return dailyTrackerServ.addFoodToToday(
				dto,
				auth
		);
	}

	/*
	 * Returnează trackerul unei anumite date.
	 *
	 * Exemplu:
	 * /daily-tracker/date/2026-07-23
	 */
	@GetMapping("/date/{date}")
	public DailyTrackerResponse getTrackerByDate(
			@PathVariable
			@DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE
			)
			LocalDate date,
			Authentication auth) {

		return dailyTrackerServ.getTrackerByDate(
				date,
				auth
		);
	}

	/*
	 * Returnează toate zilele salvate
	 * ale utilizatorului autentificat.
	 */
	@GetMapping("/history")
	public List<DailyTrackerResponse> getHistory(
			Authentication auth) {

		return dailyTrackerServ.getTrackerHistory(
				auth
		);
	}

	/*
	 * Șterge o masă din Daily Tracker.
	 *
	 * ID-ul primit este ID-ul obiectului
	 * DailyTrackerFood, nu ID-ul Food.
	 */
	@DeleteMapping("/foods/{dailyTrackerFoodId}")
	public DailyTrackerResponse deleteFood(
			@PathVariable Long dailyTrackerFoodId,
			Authentication auth) {

		return dailyTrackerServ.deleteFood(
				dailyTrackerFoodId,
				auth
		);
	}
}