import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  authService = inject(AuthService);
  notificationService = inject(NotificationService);
  router = inject(Router);

  firstName = '';
  lastName = '';
  email = '';
  password = '';
  tel = '0751622684';
  age = 18;

  register() {

    const request = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
      tel: this.tel,
      age: this.age
    };

    this.authService.register(request).subscribe({

      next: (response) => {
        this.notificationService.showSuccess('User created!');

        this.router.navigate(['/login']);
        console.log(response);
      },

      error: (err) => {
        this.notificationService.showError('Register failed!');

        console.error(err);
      }
    });
  }

}
