import { Component, inject } from '@angular/core';
import { AuthService } from '../authService/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  private authService = inject(AuthService);

  email = '';
  password = '';

  login() {

    const request = {
      email: this.email,
      password: this.password
    };

    this.authService.login(request).subscribe({

      next: (token) => {
        this.authService.saveToken(token);

        console.log('LOGIN SUCCESS');
        console.log(token);
      },

      error: (err) => {
        console.error(err);
      }
    });
  }
}
