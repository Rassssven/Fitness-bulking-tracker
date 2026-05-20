import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private authService = inject(AuthService);
  private notificationService = inject(NotificationService);

  email = '';
  password = '';

  login() {

    this.authService.login(this.email, this.password);

    console.log("Logged in!");

    this.notificationService.showSuccess('Login successful!');
  }
}
