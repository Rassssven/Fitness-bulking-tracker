import { Component, inject, OnInit, signal } from '@angular/core';
import { ExerciseService } from '../../../services/HTTP/exercise-service';
import { NotificationService } from '../../../shared/notification-service';
import { Exercise } from '../../../models/exercise';
import { CreateExerciseRequest } from '../../../models/DTO/CreateExerciseRequest';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../auth/authService/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  exercises = signal<Exercise[]>([]);

  search = "";
  category = "";

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

    this.route.queryParams.subscribe(params => {
      this.search = params['search'] || '';
      this.category = params['category'] || '';
      this.loadExercises();
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

  addExerciseToSaved(id: number) {

    this.exServ.addExerciseToSaved(id).subscribe({
      next: () => {
        this.notifService.showSuccess("Exercise saved!")
      }
    })

  }

  /* Search & Sort */

  loadExercises() {

    this.exServ.getExercisesFiltered(this.search, this.category).subscribe({
      next: exs => {
        this.exercises.set(exs);
      }
    })

  }

  onSearch(event: Event) {
    const input = event.target as HTMLInputElement;
    this.search = input.value;

    this.updateQueryParams();
  }

  onCategoryChange(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.category = select.value;

    this.updateQueryParams();
  }

  updateQueryParams() {

    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        search: this.search,
        category: this.category
      },

      queryParamsHandling: 'merge'
    });
    
  }

}
