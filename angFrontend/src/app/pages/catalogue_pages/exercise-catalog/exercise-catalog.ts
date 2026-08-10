import { Component, inject, OnInit, signal } from '@angular/core';
import { ExerciseService } from '../../../services/HTTP/exercise-service';
import { NotificationService } from '../../../shared/notification-service';
import { Exercise } from '../../../models/exercise';
import { CreateExerciseRequest } from '../../../models/DTO/CreateExerciseRequest';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../auth/authService/auth.service';

@Component({
  selector: 'app-exercise-catalog',
  imports: [FormsModule],
  templateUrl: './exercise-catalog.html',
  styleUrl: './exercise-catalog.css',
})
export class ExerciseCatalog implements OnInit {

  exServ = inject(ExerciseService);
  notifService = inject(NotificationService);
  private auth = inject(AuthService);

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

  isAdmin() {
    return this.auth.getCurrentUser()?.role === 'ADMIN';
  }

  createExercise() {

    this.exServ.createExercise(this.exData).subscribe({
      next: response => {
        console.log(response);
        this.notifService.showSuccess("Exercise added!");
      }
    });

  }

  deleteExercise(exId: number) {

    this.exServ.deleteExercise(exId).subscribe({
      next: () => {

        this.exercises.update(ex => 
          ex.filter(ex => ex.id !== exId)
        )

        this.notifService.showSuccess("Exercise deleted succsesfully.");
      }
    })

  }

}
