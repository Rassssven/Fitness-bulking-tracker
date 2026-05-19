import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../../models/login-request';
import { RegisterRequest } from '../../models/register-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = signal(false);

  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/auth';

  login(data: LoginRequest) {

    return this.http.post(
      `${this.apiUrl}/login`,
      data,
      {
        responseType: 'text'
      }
    );
  }

  register(data: RegisterRequest) {

    return this.http.post(
      `${this.apiUrl}/register`,
      data,
      {
        responseType: 'text'
      }
    );
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }
}