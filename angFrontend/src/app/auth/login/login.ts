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

    this.authService.login(this.email, this.password);

    console.log("Logged in!");
  }
}
