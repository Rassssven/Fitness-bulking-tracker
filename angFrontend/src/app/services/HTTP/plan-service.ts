import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Plan } from '../../models/plan';
import { map } from 'rxjs';
import { Goal } from '../../models/goal';

@Injectable({
  providedIn: 'root',
})
export class PlanService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/plans';

  createFullPlan(goalData: {
    type: string,
    targetWeight: number,
    duration: number
  }) {
    return this.http.post<Plan>(`${this.apiUrl}/create-full-plan`, goalData);
  }

  createPlan(plan: {
    name: string;
    type: string;
  }) {
    return this.http.post<Plan>(this.apiUrl, plan);
  }

  getPlans() {
    return this.http.get<Plan[]>(this.apiUrl);
  }

  getPlanById(id: number) {
    return this.http.get<Plan>(`${this.apiUrl}/${id}`);
  }

  deletePlan(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  updatePlan(
    id: number, 
    plan: {
      name: string;
      type: string;
    }) {
    return this.http.put<Plan>(`${this.apiUrl}/${id}`, plan);
  }

  /* Exercises */

  transformPlan() {
    return this.http.get<Plan[]>(this.apiUrl)
      .pipe(
        map(
          plans => 
            plans.filter(plans => plans.type === "BULK")
                 .map(plans => ({...plans, name: "Bulk" + plans.name}))
        )
      )
  }

  sortByTargetWeight() {
    return this.http.get<Goal[]>(this.apiUrl)
      .pipe(
        map(goals => 
            [...goals].sort((a, b) => a.targetWeight - b.targetWeight))
      )
  }

}
