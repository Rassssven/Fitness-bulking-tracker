import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../../models/register-request';
import { NotificationService } from '../../shared/notification-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/auth';
  private notifService = inject(NotificationService);

  currentUser: { email: string } | null = null;

  login(email: string, password: string) {

    const basicAuth =
      'Basic ' + btoa(email + ':' + password);

    localStorage.setItem('auth', basicAuth);
    localStorage.setItem('email', email);

    this.currentUser = {
      email: email
    };
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
        localStorage.getItem('email')
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

    this.notifService.showSuccess('Logged out successfully');
  }
}