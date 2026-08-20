import { Component, inject, OnInit, signal } from '@angular/core';
import { NotificationService } from '../../shared/notification-service';
import { FoodService } from '../../services/HTTP/food-service';
import { ExerciseService } from '../../services/HTTP/exercise-service';
import { Exercise } from '../../models/exercise';
import { SavedFood } from '../../models/savedFood';

@Component({
  selector: 'app-saved-info',
  imports: [],
  templateUrl: './saved-info.html',
  styleUrl: './saved-info.css',
})
export class SavedInfo implements OnInit {

  exServ = inject(ExerciseService);
  foodServ = inject(FoodService);
  notifService = inject(NotificationService);

  exercises = signal<Exercise[]>([]);
  foods = signal<SavedFood[]>([]);

  ngOnInit() {

    this.foodServ.getSavedFoods().subscribe({
      next: foodss => {
        this.foods.set(foodss);
      }
    })

    this.exServ.getSavedExercises().subscribe({
      next: ex => {
        this.exercises.set(ex);
      }
    })

  }

}
