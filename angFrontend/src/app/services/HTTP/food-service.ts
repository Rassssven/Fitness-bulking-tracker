import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class FoodService {

  private http = inject(HttpClient);

  private apiUrl = 'https://localhost:8080/foods';

  

}
