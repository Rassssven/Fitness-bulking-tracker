import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Goal } from '../../models/goal';

@Injectable({
  providedIn: 'root',
})
export class GoalService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/goals';

  getGoal() {
    return this.http.get<Goal>(this.apiUrl);
  }


}
