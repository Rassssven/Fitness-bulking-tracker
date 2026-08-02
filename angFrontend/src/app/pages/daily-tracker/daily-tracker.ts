import { CommonModule } from '@angular/common';
import {
  Component,
  computed,
  inject,
  OnInit,
  signal
} from '@angular/core';

import {
  FormBuilder,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { DailyTracker } from '../../models/dailyTracker';
import { DailyTrackerFood } from '../../models/dailyTrackerFood';
import { AddDailyFoodRequest } from '../../models/DTO/AddDailyFoodRequest';
import { DailyTrackerService } from '../../services/HTTP/daily-tracker-service';
import { NotificationService } from '../../shared/notification-service';

@Component({
  selector: 'app-daily-tracker',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './daily-tracker.html',
  styleUrl: './daily-tracker.css',
})
export class DailyTrackerPage implements OnInit {

  private dailyTrackerService = inject(DailyTrackerService);
  private notificationService = inject(NotificationService);
  private formBuilder = inject(FormBuilder);

  tracker = signal<DailyTracker | null>(null);
  history = signal<DailyTracker[]>([]);

  todayDate = signal('');

  isLoading = signal(true);
  isSaving = signal(false);
  isHistoryLoading = signal(false);

  showHistory = signal(false);
  showMealForm = signal(false);

  editingMealId = signal<number | null>(null);
  deletingMealId = signal<number | null>(null);

  errorMessage = signal('');

  isViewingToday = computed(() => {

    const currentTracker = this.tracker();

    if (!currentTracker) {
      return false;
    }

    return currentTracker.date === this.todayDate();
  });

  mealForm = this.formBuilder.nonNullable.group({

    name: ['', [
      Validators.required,
      Validators.minLength(2)
    ]],

    calories: [0, [
      Validators.required,
      Validators.min(0)
    ]],

    protein: [0, [
      Validators.required,
      Validators.min(0)
    ]],

    carbs: [0, [
      Validators.required,
      Validators.min(0)
    ]],

    fat: [0, [
      Validators.required,
      Validators.min(0)
    ]],

    description: ['']

  });

  ngOnInit() {
    this.loadTodayTracker();
  }

  loadTodayTracker() {

    this.isLoading.set(true);
    this.errorMessage.set('');
    this.closeMealForm();

    this.dailyTrackerService.getTodayTracker().subscribe({

      next: response => {

        this.tracker.set(response);
        this.todayDate.set(response.date);

        this.isLoading.set(false);

        this.loadHistory();
      },

      error: error => {

        console.error(error);

        this.errorMessage.set(
          'Daily tracker could not be loaded.'
        );

        this.isLoading.set(false);
      }

    });
  }

  loadHistory() {

    this.isHistoryLoading.set(true);

    this.dailyTrackerService.getTrackerHistory().subscribe({

      next: response => {

        this.history.set(response);
        this.isHistoryLoading.set(false);
      },

      error: error => {

        console.error(error);

        this.isHistoryLoading.set(false);

        this.notificationService.showError(
          'History could not be loaded!'
        );
      }

    });
  }

  toggleHistory() {

    this.showHistory.update(value => !value);

    if (this.showHistory()) {
      this.loadHistory();
    }
  }

  openDate(date: string) {

    if (!date) {
      this.notificationService.showError(
        'Please select a date!'
      );

      return;
    }

    if (date > this.todayDate()) {
      this.notificationService.showError(
        'A future date cannot be selected!'
      );

      return;
    }

    const existingTracker = this.history().find(
      historyTracker => historyTracker.date === date
    );

    if (existingTracker) {
      this.selectHistoryTracker(existingTracker);
      return;
    }

    const emptyTracker: DailyTracker = {
      id: 0,
      date: date,
      totalCalories: 0,
      totalProtein: 0,
      totalCarbs: 0,
      totalFat: 0,
      meals: []
    };

    this.tracker.set(emptyTracker);

    this.showHistory.set(false);
    this.closeMealForm();

    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  }

  selectHistoryTracker(selectedTracker: DailyTracker) {

    this.tracker.set(selectedTracker);

    this.showHistory.set(false);
    this.closeMealForm();

    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  }

  returnToToday() {
    this.loadTodayTracker();
  }

  openMealForm() {

    this.editingMealId.set(null);

    this.mealForm.reset({
      name: '',
      calories: 0,
      protein: 0,
      carbs: 0,
      fat: 0,
      description: ''
    });

    this.showMealForm.set(true);
  }

  openEditMealForm(meal: DailyTrackerFood) {

    this.editingMealId.set(meal.id);

    this.mealForm.setValue({
      name: meal.name,
      calories: meal.calories,
      protein: meal.protein,
      carbs: meal.carbs,
      fat: meal.fat,
      description: meal.description ?? ''
    });

    this.showMealForm.set(true);

    window.scrollTo({
      top: 150,
      behavior: 'smooth'
    });
  }

  closeMealForm() {

    this.showMealForm.set(false);
    this.editingMealId.set(null);

    this.mealForm.reset({
      name: '',
      calories: 0,
      protein: 0,
      carbs: 0,
      fat: 0,
      description: ''
    });
  }

  saveFood() {

    if (this.mealForm.invalid) {
      this.mealForm.markAllAsTouched();
      return;
    }

    const currentTracker = this.tracker();

    if (!currentTracker) {
      return;
    }

    const foodData: AddDailyFoodRequest =
      this.mealForm.getRawValue();

    const mealId = this.editingMealId();

    const request = mealId === null
      ? this.dailyTrackerService.addFoodToDate(
        currentTracker.date,
        foodData
      )
      : this.dailyTrackerService.updateFood(
        mealId,
        foodData
      );

    this.isSaving.set(true);

    request.subscribe({

      next: updatedTracker => {

        const wasEditing = mealId !== null;

        this.tracker.set(updatedTracker);
        this.updateHistoryEntry(updatedTracker);

        this.isSaving.set(false);
        this.closeMealForm();

        this.notificationService.showSuccess(
          wasEditing
            ? 'Meal updated successfully!'
            : 'Meal added successfully!'
        );
      },

      error: error => {

        console.error(error);

        this.isSaving.set(false);

        this.notificationService.showError(
          mealId !== null
            ? 'Meal could not be updated!'
            : 'Meal could not be added!'
        );
      }

    });
  }

  deleteFood(dailyTrackerFoodId: number) {

    const confirmed = window.confirm(
      'Are you sure you want to delete this meal?'
    );

    if (!confirmed) {
      return;
    }

    this.deletingMealId.set(dailyTrackerFoodId);

    this.dailyTrackerService
      .deleteFood(dailyTrackerFoodId)
      .subscribe({

        next: updatedTracker => {

          this.tracker.set(updatedTracker);
          this.updateHistoryEntry(updatedTracker);

          this.deletingMealId.set(null);

          this.notificationService.showSuccess(
            'Meal deleted successfully!'
          );
        },

        error: error => {

          console.error(error);

          this.deletingMealId.set(null);

          this.notificationService.showError(
            'Meal could not be deleted!'
          );
        }

      });
  }

  private updateHistoryEntry(updatedTracker: DailyTracker) {

    this.history.update(currentHistory => {

      const trackerExists = currentHistory.some(
        historyTracker =>
          historyTracker.id === updatedTracker.id
      );

      const updatedHistory = trackerExists
        ? currentHistory.map(historyTracker =>
          historyTracker.id === updatedTracker.id
            ? updatedTracker
            : historyTracker
        )
        : [
          updatedTracker,
          ...currentHistory
        ];

      return updatedHistory.sort(
        (firstTracker, secondTracker) =>
          secondTracker.date.localeCompare(
            firstTracker.date
          )
      );
    });
  }
}
