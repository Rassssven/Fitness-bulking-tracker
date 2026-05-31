import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Plan } from '../../models/plan';

@Injectable({
  providedIn: 'root',
})
export class PlanService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/plans';

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

}
