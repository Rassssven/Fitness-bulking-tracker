import { Component, inject, OnInit, signal } from '@angular/core';
import { AuthService } from '../../auth/authService/auth.service';
import { PlanService } from '../../services/HTTP/plan-service';
import { Plan } from '../../models/plan';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../../services/HTTP/user-service';
import { FormsModule } from '@angular/forms';

interface UpdateUserDTO {
  age: number;
  weight: number;
  height: number;
  activityLevel: string;
}

@Component({
  selector: 'app-profile-dashboard',
  imports: [CommonModule, FormsModule],
  templateUrl: './profile-dashboard.html',
  styleUrl: './profile-dashboard.css',
})
export class ProfileDashboard implements OnInit {

  private authService = inject(AuthService);
  private planService = inject(PlanService);
  private userService = inject(UserService);
  private router = inject(Router);

  plans = signal<Plan[]>([]);
  //private cdr = inject(ChangeDetectorRef); 
  
  userData: UpdateUserDTO = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: ''
  }

  currentUser = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: '',
    firstName: '',
    lastName: '',
    tel: 0
  }

  ngOnInit() {

    this.planService.getPlans().subscribe({
      next: (response) => {
        console.log(response);

        this.plans.set(response);
        //this.cdr.detectChanges();
      }
    });

    this.userService.getCurrentUserLogged().subscribe({
      next: user => {
        console.log(user);

        this.currentUser = user;
      }
    });

  }

  goToPlan(id: number) {
    this.router.navigate(['/customize-plan-page/' + id]);
  }

  updateInfo() {

    const userData = {
      age: this.userData.age,
      weight: this.userData.weight,
      height: this.userData.height,
      activityLevel: this.userData.activityLevel
    }

    this.userService.updateAccountInfo(userData).subscribe({
      next: (response) => {
        console.log('User info updated successfully:', response);
      }
    });
  }

}
