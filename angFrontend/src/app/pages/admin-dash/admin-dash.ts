import { Component, inject, OnInit, signal } from '@angular/core';
import { UserService } from '../../services/HTTP/user-service';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Product } from '../../models/product';

@Component({
  selector: 'app-admin-dash',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-dash.html',
  styleUrl: './admin-dash.css',
})
export class AdminDash implements OnInit {

  private userService = inject(UserService);

  userData = signal<User[]>([]);
  product = signal<Product | null>(null);

  role = '';

  ngOnInit() {

    this.userService.getUsers().subscribe({
      next: (response) => {
        console.log(response);

        this.userData.set(response);
      }
    });
  }

  setRole(userId: number, role: string) {

    this.userService.updateRole(userId, role).subscribe({
      next: () => {
        console.log(`User with id ${userId} updated to ${role}`);
      }
    });
  }

  searchUsers(name: string) {

    this.userService.searchUser(name).subscribe({
      next: users => {
        this.userData.set(users);
      }
    });
  }


}
