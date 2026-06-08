import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Meal } from '../../models/meal';

@Injectable({
  providedIn: 'root',
})
export class FoodService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/foods';

  createFood(mealData: Meal) {
    return this.http.post<Meal>(this.apiUrl, mealData);
  }


}
