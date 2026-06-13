import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Meal } from '../../models/meal';
import { PlanFood } from '../../models/planFood';
import { CreatePlanMealRequest } from '../../models/DTO/CreatePlanFoodRequest';

@Injectable({
  providedIn: 'root',
})
export class FoodService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/foods';
  private apiUrlPlan = 'http://localhost:8080/plan-food';

  createPlanFood(planId: number, mealData: CreatePlanMealRequest) {
    return this.http.post<Meal>(`${this.apiUrlPlan}/${planId}`, mealData);
  }

  getFoods(planId: number) {
    return this.http.get<PlanFood[]>(`${this.apiUrlPlan}/${planId}`)
  }

  deleteFood(id: number) {
    return this.http.delete(`${this.apiUrlPlan}/${id}`);
  }




}
