import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { DailyTracker } from '../../models/dailyTracker';
import { AddDailyFoodRequest } from '../../models/DTO/AddDailyFoodRequest';

@Injectable({
  providedIn: 'root',
})
export class DailyTrackerService {

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/daily-tracker';

  getTodayTracker() {
    return this.http.get<DailyTracker>(
      `${this.apiUrl}/today`
    );
  }

  addFoodToToday(foodData: AddDailyFoodRequest) {
    return this.http.post<DailyTracker>(
      `${this.apiUrl}/today/foods`,
      foodData
    );
  }

  addFoodToDate(
    date: string,
    foodData: AddDailyFoodRequest
  ) {
    return this.http.post<DailyTracker>(
      `${this.apiUrl}/date/${date}/foods`,
      foodData
    );
  }

  getTrackerByDate(date: string) {
    return this.http.get<DailyTracker>(
      `${this.apiUrl}/date/${date}`
    );
  }

  getTrackerHistory() {
    return this.http.get<DailyTracker[]>(
      `${this.apiUrl}/history`
    );
  }

  updateFood(
    dailyTrackerFoodId: number,
    foodData: AddDailyFoodRequest
  ) {
    return this.http.put<DailyTracker>(
      `${this.apiUrl}/foods/${dailyTrackerFoodId}`,
      foodData
    );
  }

  deleteFood(dailyTrackerFoodId: number) {
    return this.http.delete<DailyTracker>(
      `${this.apiUrl}/foods/${dailyTrackerFoodId}`
    );
  }
}
