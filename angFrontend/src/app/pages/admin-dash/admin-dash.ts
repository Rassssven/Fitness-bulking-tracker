import { Component, inject, OnInit, signal } from '@angular/core';
import { UserService } from '../../services/HTTP/user-service';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Product } from '../../models/product';
import { NotificationService } from '../../shared/notification-service';
import { catchError, EMPTY, map, tap } from 'rxjs';

@Component({
  selector: 'app-admin-dash',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-dash.html',
  styleUrl: './admin-dash.css',
})
export class AdminDash implements OnInit {

  private userService = inject(UserService);
  private notifService = inject(NotificationService);

  userData = signal<User[]>([]);
  product = signal<Product | null>(null);

  role = '';

  ngOnInit() {

    this.userService.getUsers()
    .pipe(
      map(response => response.map(
        user => ({
          ...user,
          fullName: user.firstName + " " + user.lastName
        })
      ))
    )
    .subscribe({
      next: (response) => {
        console.log(response);

        this.userData.set(response);
      }
    });
  }

  deleteUser(id: number) {
    
    this.userService.deleteUser(id)
    .pipe(
      tap(() => {
        console.log(`Deleting user with id: ${id}`);
    })
    )
    .subscribe({
      next: () => {
        console.log("User deleted!");

        this.userData.update(users =>
          users.filter(user => user.id !== id)
        )

        this.notifService.showSuccess("Food deleted successfully");
      }
    });
  }

  setRole(userId: number, role: string) {

    this.userService.updateRole(userId, role)
    .pipe(
      catchError(error => {
        console.log(error);

        return EMPTY;
      })
    )
    .subscribe({
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
