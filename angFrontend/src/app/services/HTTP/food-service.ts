import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { PlanFood } from '../../models/planFood';
import { CreatePlanMealRequest } from '../../models/DTO/CreatePlanFoodRequest';
import { CreateFoodRequest } from '../../models/DTO/CreateFoodRequest';
import { Meal } from '../../models/meal';

@Injectable({
  providedIn: 'root',
})
export class FoodService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/foods';
  private apiUrlPlan = 'http://localhost:8080/plan-food';

  createFood(mealData: CreateFoodRequest) {
    return this.http.post<Meal>(this.apiUrl, mealData);
  }

  getFoods() {
    return this.http.get<Meal[]>(this.apiUrl);
  }

  deleteFood(foodId: number) {
    return this.http.delete(`${this.apiUrl}/${foodId}`);
  }


  /* Plan Food */

  createPlanFood(planId: number, mealData: CreatePlanMealRequest) {
    return this.http.post<PlanFood>(`${this.apiUrlPlan}/${planId}`, mealData);
  }

  getPlanFoods(planId: number) {
    return this.http.get<PlanFood[]>(`${this.apiUrlPlan}/${planId}`)
  }

  deletePlanFood(id: number) {
    return this.http.delete(`${this.apiUrlPlan}/${id}`);
  }




}
