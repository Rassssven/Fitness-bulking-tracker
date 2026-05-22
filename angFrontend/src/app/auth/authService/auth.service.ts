import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../../models/register-request';
import { NotificationService } from '../../shared/notification-service';
import { LoginRequest } from '../../models/login-request';
import { LoginResponse } from '../../models/login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  http = inject(HttpClient);
  apiUrl = 'http://localhost:8080/auth';
  notifService = inject(NotificationService);

  currentUser: {
    email: string | null,
    firstName: string | null
  } = this.getCurrentUser();

  login(data: LoginRequest) {
    return this.http.post<LoginResponse>(
      `${this.apiUrl}/login`,
      data
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

  getCurrentUser() {
    return {
      email: localStorage.getItem('email'),
      firstName: localStorage.getItem('firstName')
    };
  }

  getAuthHeader(): string {
    return localStorage.getItem('auth') || '';
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('auth');
  }

  logout() {
    localStorage.removeItem('auth');
    localStorage.removeItem('email');
    localStorage.removeItem('firstName');

    this.currentUser = {
      email: null,
      firstName: null
    };

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