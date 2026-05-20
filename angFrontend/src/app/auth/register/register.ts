import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  private authService = inject(AuthService);
  private notificationService = inject(NotificationService);

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

        console.log(response);
      },

      error: (err) => {
        this.notificationService.showError('Register failed!');

        console.error(err);
      }
    });
  }

}
