import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private api = "http://localhost:8080/api/test";

  constructor(private http: HttpClient) {}

  getMessage(): Observable<string> {
    return this.http.get(this.api, { responseType: 'text' });
  }
}