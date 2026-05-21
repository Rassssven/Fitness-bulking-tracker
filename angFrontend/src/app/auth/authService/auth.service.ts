import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../../models/register-request';
import { NotificationService } from '../../shared/notification-service';
import { LoginRequest } from '../../models/login-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/auth';
  private notifService = inject(NotificationService);

  currentUser: { email: string, name: string } | null = null;

  login(data: LoginRequest) {
    return this.http.post(
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

      email:
        localStorage.getItem('email'),
      name:
        localStorage.getItem('name')
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
    localStorage.removeItem('name');

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