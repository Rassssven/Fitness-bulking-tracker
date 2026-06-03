import { Component, inject } from '@angular/core';
import { RouterLink } from "@angular/router";
import { AuthService } from '../../auth/authService/auth.service';

@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {

  auth = inject(AuthService);

  currentUser() {
    return this.auth.getCurrentUser();
  }

  isLoggedIn() {
    return this.auth.isLoggedIn();
  }

  logout() {
    this.auth.logout();
  }

  isAdmin() {
    return this.auth.getCurrentUser()?.role === 'ADMIN';
  }

}
