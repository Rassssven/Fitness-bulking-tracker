package proiect.demo.web.ang_spring.Services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proiect.demo.web.ang_spring.DTO.AddDailyFoodRequest;
import proiect.demo.web.ang_spring.DTO.DailyTrackerFoodResponse;
import proiect.demo.web.ang_spring.DTO.DailyTrackerResponse;
import proiect.demo.web.ang_spring.Entities.DailyTracker;
import proiect.demo.web.ang_spring.Entities.User;
import proiect.demo.web.ang_spring.Entities.Enums.FoodStatus;
import proiect.demo.web.ang_spring.Entities.Food.DailyTrackerFood;
import proiect.demo.web.ang_spring.Entities.Food.Food;
import proiect.demo.web.ang_spring.db.DailyTrackerFoodRepository;
import proiect.demo.web.ang_spring.db.DailyTrackerRepository;
import proiect.demo.web.ang_spring.db.FoodRepository;
import proiect.demo.web.ang_spring.db.UserRepository;

@Service
public class DailyTrackerService {

	private static final ZoneId APP_TIME_ZONE =
			ZoneId.of("Europe/Bucharest");

	private final DailyTrackerRepository dailyTrackerRepo;
	private final DailyTrackerFoodRepository dailyTrackerFoodRepo;
	private final FoodRepository foodRepo;
	private final UserRepository userRepo;

	public DailyTrackerService(
			DailyTrackerRepository dailyTrackerRepo,
			DailyTrackerFoodRepository dailyTrackerFoodRepo,
			FoodRepository foodRepo,
			UserRepository userRepo) {

		this.dailyTrackerRepo = dailyTrackerRepo;
		this.dailyTrackerFoodRepo = dailyTrackerFoodRepo;
		this.foodRepo = foodRepo;
		this.userRepo = userRepo;
	}

	/*
	 * Returnează trackerul zilei curente.
	 * Dacă nu există, îl creează automat.
	 */
	@Transactional    public DailyTrackerResponse getTodayTracker(
			Authentication auth) {

		User user = getCurrentUser(auth);

		DailyTracker tracker =
				getOrCreateTodayTracker(user);

		return mapToResponse(tracker);
	}

	/*
	 * Adaugă o masă în trackerul zilei curente.
	 */
	@Transactional
	public DailyTrackerResponse addFoodToToday(
			AddDailyFoodRequest dto,
			Authentication auth) {

		validateFoodRequest(dto);

		User user = getCurrentUser(auth);

		DailyTracker tracker =
				getOrCreateTodayTracker(user);

		Food food = new Food();

		food.setName(dto.getName());
		food.setCalories(dto.getCalories());
		food.setProtein(dto.getProtein());
		food.setCarbs(dto.getCarbs());
		food.setFat(dto.getFat());
		food.setDescription(dto.getDescription());
		food.setStatus(FoodStatus.PRIVATE);
		food.setUser(user);

		food = foodRepo.save(food);

		int nextMealNumber = tracker.getFoods()
				.stream()
				.mapToInt(
						DailyTrackerFood::getMealNumber
				)
				.max()
				.orElse(0) + 1;

		DailyTrackerFood trackerFood =
				new DailyTrackerFood();

		trackerFood.setFood(food);
		trackerFood.setDailyTracker(tracker);
		trackerFood.setMealNumber(nextMealNumber);

		dailyTrackerFoodRepo.save(trackerFood);

		return mapToResponse(tracker);
	}

	/*
	 * Returnează trackerul unei date din istoric.
	 */
	@Transactional(readOnly = true)
	public DailyTrackerResponse getTrackerByDate(
			LocalDate date,
			Authentication auth) {

		User user = getCurrentUser(auth);

		DailyTracker tracker = dailyTrackerRepo
				.findByUserIdAndDate(
						user.getId(),
						date
				)
				.orElseThrow(() ->
						new RuntimeException(
								"No tracker found for this date!"
						)
				);

		return mapToResponse(tracker);
	}

	/*
	 * Returnează toate zilele salvate ale utilizatorului.
	 */
	@Transactional(readOnly = true)
	public List<DailyTrackerResponse> getTrackerHistory(
			Authentication auth) {

		User user = getCurrentUser(auth);

		return dailyTrackerRepo
				.findByUserIdOrderByDateDesc(
						user.getId()
				)
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	/*
	 * Șterge o masă numai dacă aparține
	 * utilizatorului autentificat.
	 */
	@Transactional
	public DailyTrackerResponse deleteFood(
			Long dailyTrackerFoodId,
			Authentication auth) {

		User user = getCurrentUser(auth);

		DailyTrackerFood trackerFood =
				dailyTrackerFoodRepo
						.findByIdAndDailyTrackerUserId(
								dailyTrackerFoodId,
								user.getId()
						)
						.orElseThrow(() ->
								new RuntimeException(
										"Daily food not found!"
								)
						);

		DailyTracker tracker =
				trackerFood.getDailyTracker();

		Food food = trackerFood.getFood();

		dailyTrackerFoodRepo.delete(trackerFood);
		dailyTrackerFoodRepo.flush();

		foodRepo.delete(food);

		return mapToResponse(tracker);
	}

	/*
	 * Găsește utilizatorul autentificat.
	 */
	private User getCurrentUser(
			Authentication auth) {

		String email = auth.getName();

		return userRepo.findByEmail(email)
				.orElseThrow(() ->
						new RuntimeException(
								"User not found!"
						)
				);
	}

	/*
	 * Caută trackerul zilei curente.
	 * Dacă nu există, creează unul nou.
	 */
	private DailyTracker getOrCreateTodayTracker(
			User user) {

		LocalDate today =
				LocalDate.now(APP_TIME_ZONE);

		return dailyTrackerRepo
				.findByUserIdAndDate(
						user.getId(),
						today
				)
				.orElseGet(() -> {

					DailyTracker tracker =
							new DailyTracker();

					tracker.setDate(today);
					tracker.setUser(user);

					return dailyTrackerRepo.save(
							tracker
					);
				});
	}

	/*
	 * Transformă DailyTracker într-un DTO.
	 */
	private DailyTrackerResponse mapToResponse(
			DailyTracker tracker) {

		List<DailyTrackerFood> trackerFoods =
				dailyTrackerFoodRepo
						.findByDailyTrackerIdOrderByMealNumberAsc(
								tracker.getId()
						);

		List<DailyTrackerFoodResponse> meals =
				trackerFoods.stream()
						.map(this::mapFoodToResponse)
						.toList();

		int totalCalories = meals.stream()
				.mapToInt(
						DailyTrackerFoodResponse::getCalories
				)
				.sum();

		int totalProtein = meals.stream()
				.mapToInt(
						DailyTrackerFoodResponse::getProtein
				)
				.sum();

		int totalCarbs = meals.stream()
				.mapToInt(
						DailyTrackerFoodResponse::getCarbs
				)
				.sum();

		int totalFat = meals.stream()
				.mapToInt(
						DailyTrackerFoodResponse::getFat
				)
				.sum();

		DailyTrackerResponse response =
				new DailyTrackerResponse();

		response.setId(tracker.getId());
		response.setDate(tracker.getDate());
		response.setTotalCalories(totalCalories);
		response.setTotalProtein(totalProtein);
		response.setTotalCarbs(totalCarbs);
		response.setTotalFat(totalFat);
		response.setMeals(meals);

		return response;
	}

	/*
	 * Transformă DailyTrackerFood într-un DTO.
	 */
	private DailyTrackerFoodResponse mapFoodToResponse(
			DailyTrackerFood trackerFood) {

		Food food = trackerFood.getFood();

		DailyTrackerFoodResponse response =
				new DailyTrackerFoodResponse();

		response.setId(trackerFood.getId());
		response.setFoodId(food.getId());
		response.setMealNumber(
				trackerFood.getMealNumber()
		);

		response.setName(food.getName());
		response.setCalories(
				food.getCalories()
		);
		response.setProtein(
				food.getProtein()
		);
		response.setCarbs(
				food.getCarbs()
		);
		response.setFat(
				food.getFat()
		);
		response.setDescription(
				food.getDescription()
		);

		return response;
	}

	/*
	 * Validează datele mesei.
	 */
	private void validateFoodRequest(
			AddDailyFoodRequest dto) {

		if (dto.getName() == null
				|| dto.getName().isBlank()) {

			throw new RuntimeException(
					"Food name is required!"
			);
		}

		if (dto.getCalories() == null
				|| dto.getCalories() < 0) {

			throw new RuntimeException(
					"Calories must be zero or greater!"
			);
		}

		if (dto.getProtein() == null
				|| dto.getProtein() < 0) {

			throw new RuntimeException(
					"Protein must be zero or greater!"
			);
		}

		if (dto.getCarbs() == null
				|| dto.getCarbs() < 0) {

			throw new RuntimeException(
					"Carbs must be zero or greater!"
			);
		}

		if (dto.getFat() == null
				|| dto.getFat() < 0) {

			throw new RuntimeException(
					"Fat must be zero or greater!"
			);
		}
	}
}