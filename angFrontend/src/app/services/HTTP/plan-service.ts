import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Plan } from '../../models/plan';

@Injectable({
  providedIn: 'root',
})
export class PlanService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/plans';

  createPlan(userId: number, plan: Plan) {
    return this.http.post<Plan>(`${this.apiUrl}/${userId}`, plan);
  }

  getPlans(userId: number) {
    return this.http.get<Plan[]>(`${this.apiUrl}/${userId}`);
  }

}
