import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { PlanExercise } from '../../models/planExercise';
import { CreatePlanExerciseRequest } from '../../models/DTO/CreatePlanExerciseRequest';
import { CreateExerciseRequest } from '../../models/DTO/CreateExerciseRequest';
import { Exercise } from '../../models/exercise';
import { AddCatalogueExerciseToPlanRequest } from '../../models/DTO/AddCatalogueExerciseToPlanRequest';
import { SavedExercise } from '../../models/savedExercise';

@Injectable({
  providedIn: 'root',
})
export class ExerciseService {

    private http = inject(HttpClient);
    private apiUrl = 'http://localhost:8080/exercise';
    private apiUrlPlan = 'http://localhost:8080/plan-exercise';
    private apiUrlSaved = 'http://localhost:8080/saved-exercises';

    createExercise(exerciseData: CreateExerciseRequest) {
        return this.http.post<Exercise>(this.apiUrl, exerciseData);
    }

    getExercises() {
        return this.http.get<Exercise[]>(this.apiUrl);
    }

    deleteExercise(exId: number) {
        return this.http.delete(`${this.apiUrl}/${exId}`);
    }

    addCatalogueExerciseToPlan(exData: AddCatalogueExerciseToPlanRequest, planId: number, exId: number) {
        return this.http.post(`${this.apiUrl}/${planId}/${exId}`, exData);
    }

    /* Plan Exercise */

    createPlanExercise(planId: number, exerciseData: CreatePlanExerciseRequest) {
        return this.http.post<PlanExercise>(`${this.apiUrlPlan}/${planId}`, exerciseData);
    }

    getPlanExercises(planId: number) {
        return this.http.get<PlanExercise[]>(`${this.apiUrlPlan}/${planId}`);
    }

    deletePlanExercise(exerciseId: number) {
        return this.http.delete(`${this.apiUrlPlan}/${exerciseId}`);
    }

    /* Saved Exercises */

    addExerciseToSaved(id: number) {
        return this.http.post<Exercise>(`${this.apiUrlSaved}/${id}`, null);
    }

    getSavedExercises() {
        return this.http.get<SavedExercise[]>(`${this.apiUrlSaved}`)
    }

    deleteSavedExercises(id: number) {
        return this.http.delete(`${this.apiUrlSaved}/${id}`);
    }

    /* Filtering */

    getExercisesFiltered(search: string, muscleGroup: string) {
        let params = new HttpParams();

        if (search) {
            params = params.set('search', search);
        }
        if (muscleGroup) {
            params = params.set('muscleGroup', muscleGroup);
        }

        return this.http.get<Exercise[]>(this.apiUrl, { params });
    }

}
