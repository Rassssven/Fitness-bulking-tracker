import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../auth/authService/auth.service';
import { PlanService } from '../../services/HTTP/plan-service';
import { Plan } from '../../models/plan';

@Component({
  selector: 'app-profile-dashboard',
  imports: [],
  templateUrl: './profile-dashboard.html',
  styleUrl: './profile-dashboard.css',
})
export class ProfileDashboard implements OnInit {

  private authService = inject(AuthService);
  private planService = inject(PlanService);

  plans: Plan[] = [];

  ngOnInit() {

    const userId = this.authService.currentUser.id;

    this.planService.getPlans(userId!).subscribe({

      next: (response) => {
        this.plans = response;
      }

    });
  }

}
