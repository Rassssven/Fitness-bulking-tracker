import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';
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
    };

    this.authService.login(request).subscribe({

      next: (token: string) => {
        localStorage.setItem('token', token);

        this.notificationService.showSuccess('Logged in successfully');

        this.router.navigate(['/profile-dashboard']);
      },

      error: (err) => {
        console.error(err);
        this.notificationService.showError('Login failed');
      }
    })
  }
}