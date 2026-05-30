import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';
import { Router } from '@angular/router';
import { CurrentUser } from '../models/current-user';
import { jwtDecode } from 'jwt-decode';

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

        const user = jwtDecode<CurrentUser>(token);
        this.authService.currentUser.set(user);

        this.notificationService.showSuccess('Logged in successfully');

        this.router.navigate(['/']);
      },

      error: (err) => {
        console.error(err);
        this.notificationService.showError('Login failed');
      }
    })
  }
}