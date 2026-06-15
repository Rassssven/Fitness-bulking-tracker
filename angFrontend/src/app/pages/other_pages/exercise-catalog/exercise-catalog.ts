import { Component, inject, OnInit, signal } from '@angular/core';
import { ExerciseService } from '../../../services/HTTP/exercise-service';
import { NotificationService } from '../../../shared/notification-service';
import { Exercise } from '../../../models/exercise';
import { CreateExerciseRequest } from '../../../models/DTO/CreateExerciseRequest';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-exercise-catalog',
  imports: [FormsModule],
  templateUrl: './exercise-catalog.html',
  styleUrl: './exercise-catalog.css',
})
export class ExerciseCatalog implements OnInit {

  exServ = inject(ExerciseService);
  notifService = inject(NotificationService);

  exercises = signal<Exercise[]>([]);

  exData: CreateExerciseRequest = {
    name: '',
    type: '',
    caloriesPerExercise: 0,
    description: '',
    muscleGroup: ''
  }

  showExerciseForm = false;

  ngOnInit() {

    this.exServ.getExercises().subscribe({
      next: response => {
        this.exercises.set(response);
      }
    });

  }

  createExercise() {

    this.exServ.createExercise(this.exData).subscribe({
      next: response => {
        console.log(response);
        this.notifService.showSuccess("Exercise added!");
      }
    });

  }

}
