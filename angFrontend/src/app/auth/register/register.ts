import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  private authService = inject(AuthService);

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
        console.log(response);
      },

      error: (err) => {
        console.error(err);
      }
    });
  }

}
