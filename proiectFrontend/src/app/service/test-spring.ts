import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TestSpringService {

  private apiUrl = 'http://localhost:8080/api/test';

  constructor(private http: HttpClient) {}

  getTest() {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}
