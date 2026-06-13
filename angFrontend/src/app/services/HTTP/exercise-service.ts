import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { PlanExercise } from '../../models/planExercise';
import { CreatePlanExerciseRequest } from '../../models/DTO/CreatePlanExerciseRequest';

@Injectable({
  providedIn: 'root',
})
export class ExerciseService {

    private http = inject(HttpClient);
    private apiUrl = 'http://localhost:8080/exercise';
    private apiUrlPlan = 'http://localhost:8080/plan-exercise';

    createPlanExercise(planId: number, exerciseData: CreatePlanExerciseRequest) {
        return this.http.post<PlanExercise>(`${this.apiUrlPlan}/${planId}`, exerciseData);
    }

    getPlanExercises(planId: number) {
        return this.http.get<PlanExercise[]>(`${this.apiUrlPlan}/${planId}`);
    }

    deletePlanExercise(exerciseId: number) {
        return this.http.delete(`${this.apiUrlPlan}/${exerciseId}`);
    }


}
