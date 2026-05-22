import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';
import { LoginResponse } from '../../models/login-response';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  authService = inject(AuthService);
  notificationService = inject(NotificationService);
  router = inject(Router);

  email = '';
  password = '';

  login() {

    const request = {
      email: this.email,
      password: this.password
    }

    this.authService.login(request).subscribe({

      next: (response: LoginResponse) => {
        localStorage.setItem('email', response.email);
        localStorage.setItem('firstName', response.firstName);
        localStorage.setItem('auth', 'true');

        this.authService.currentUser = {
          email: response.email,
          firstName: response.firstName
        }

        this.notificationService.showSuccess('Logged in successfully');

        this.router.navigate(['']);
        console.log(response);
      },

      error: (err) => {
        console.error(err);
        this.notificationService.showError('Login failed');
      }
    })
  }
}