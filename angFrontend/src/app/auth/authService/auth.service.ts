import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../models/register-request';
import { NotificationService } from '../../shared/notification-service';
import { LoginRequest } from '../models/login-request';
import { jwtDecode } from 'jwt-decode';
import { CurrentUser } from '../models/current-user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  http = inject(HttpClient);
  apiUrl = 'http://localhost:8080/auth';
  notifService = inject(NotificationService);

  currentUser = signal<CurrentUser | null>(null);

  constructor() {
    const token = localStorage.getItem('token');

    if (token) {
      this.currentUser.set(jwtDecode<CurrentUser>(token));
    }
  }

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

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getCurrentUser(): CurrentUser | null {
    const token = this.getToken();

    if(!token) return null;

    return jwtDecode<CurrentUser>(token);
  }

  logout() {
    localStorage.removeItem('token');

    this.notifService.showSuccess('Logged out successfully');
  }

  /*
  login(email: string, password: string) {

    const basicAuth =
      'Basic ' + btoa(email + ':' + password);

    const name = email.split('@')[0];

    localStorage.setItem('auth', basicAuth);
    localStorage.setItem('email', email);
    localStorage.setItem('name', name);

    this.currentUser = {
      email: email,
      name: name
    };
  }
  */
}