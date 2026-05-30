import { Component, inject, OnInit, signal } from '@angular/core';
import { AuthService } from '../../auth/authService/auth.service';
import { PlanService } from '../../services/HTTP/plan-service';
import { Plan } from '../../models/plan';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-dashboard',
  imports: [CommonModule],
  templateUrl: './profile-dashboard.html',
  styleUrl: './profile-dashboard.css',
})
export class ProfileDashboard implements OnInit {

  private authService = inject(AuthService);
  private planService = inject(PlanService);
  private router = inject(Router);

  plans = signal<Plan[]>([]);
  //private cdr = inject(ChangeDetectorRef);  

  ngOnInit() {

    this.planService.getPlans().subscribe({
      next: (response) => {
        console.log(response);

        this.plans.set(response);
        //this.cdr.detectChanges();
      }

    });
  }

  goToPlan(id: number) {
    this.router.navigate(['/customize-plan-page/' + id]);
  }

}
