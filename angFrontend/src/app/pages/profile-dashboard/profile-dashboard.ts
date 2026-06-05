import { Component, inject, OnInit, signal } from '@angular/core';
import { AuthService } from '../../auth/authService/auth.service';
import { PlanService } from '../../services/HTTP/plan-service';
import { Plan } from '../../models/plan';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../../services/HTTP/user-service';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../shared/notification-service';

interface UpdateUserDTO {
  age: number | null;
  weight: number | null;
  height: number | null;
  activityLevel: string | null;
}

@Component({
  selector: 'app-profile-dashboard',
  imports: [CommonModule, FormsModule],
  templateUrl: './profile-dashboard.html',
  styleUrl: './profile-dashboard.css',
})
export class ProfileDashboard implements OnInit {

  private authService = inject(AuthService);
  private notifService = inject(NotificationService);
  private planService = inject(PlanService);
  private userService = inject(UserService);
  private router = inject(Router);

  plans = signal<Plan[]>([]);
  //private cdr = inject(ChangeDetectorRef); 
  
  userData: UpdateUserDTO = {
    age: null,
    weight: null,
    height: null,
    activityLevel: null
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

    const dto: Partial<UpdateUserDTO> = {};

    if(this.userData.age !== null) {
      dto.age = this.userData.age;
    }

    if(this.userData.weight !== null) {
      dto.weight = this.userData.weight;
    }

    if(this.userData.height !== null) {
      dto.height = this.userData.height;
    }

    if(this.userData.activityLevel) {
      dto.activityLevel = this.userData.activityLevel;
    }

    this.userService.updateAccountInfo(dto).subscribe({
      next: (response) => {
        console.log('User info updated successfully:', response);

        this.notifService.showSuccess("User updated!");

        this.userData = {
          age: null,
          weight: null,
          height: null,
          activityLevel: null
        };
      }
    });
  }

}
