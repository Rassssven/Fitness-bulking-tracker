import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../auth/authService/auth.service';
import { PlanService } from '../../services/HTTP/plan-service';
import { Plan } from '../../models/plan';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile-dashboard',
  imports: [CommonModule],
  templateUrl: './profile-dashboard.html',
  styleUrl: './profile-dashboard.css',
})
export class ProfileDashboard implements OnInit {

  private authService = inject(AuthService);
  private planService = inject(PlanService);

  plans: Plan[] = [];

  ngOnInit() {

    this.planService.getPlans().subscribe({

      next: (response) => {
        console.log(response);

        this.plans = response;
      }

    });
  }

}
