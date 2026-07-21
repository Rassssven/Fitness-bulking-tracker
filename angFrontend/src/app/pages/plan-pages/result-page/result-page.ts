import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CalcData } from '../../../models/info-gym';
import { Recommendations } from '../../../services/Recommendations/recommendations';
import { NotificationService } from '../../../shared/notification-service';
import { PlanService } from '../../../services/HTTP/plan-service';
import { AuthService } from '../../../auth/authService/auth.service';
import { UserService } from '../../../services/HTTP/user-service';

@Component({
  selector: 'app-result-page',
  imports: [],
  templateUrl: './result-page.html',
  styleUrl: './result-page.css',
})
export class ResultPage implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);  
  private recommendations = inject(Recommendations);
  private planService = inject(PlanService);
  private notifService = inject(NotificationService);
  private userService = inject(UserService);
  private authService = inject(AuthService);

  calcData: CalcData = {
    plan: 'custom',
    weight: 0,
    targetWeight: 0,
    duration: 0
  }

  recommendationsData: string[] = [];
  
  savePlanAndGoal() {

    const goalData = {
      type: this.calcData.plan,
      targetWeight: this.calcData.targetWeight,
      duration: this.calcData.duration
    }

    console.log(goalData);

    this.planService.createFullPlan(goalData).subscribe({
      next: response => {
        console.log('Plan saved successfully:', response);
        this.router.navigate(['/customize-plan-page', response.id], {state: {data: this.calcData}});

        this.notifService.showSuccess('Plan saved successfully!');
      },

      error: (error) => {
        console.error('Error saving plan:', error);
        this.notifService.showError('Error saving plan.');
      }

    });

  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.calcData = {
        plan: params['plan'],
        weight: Number(params['weight']),
        targetWeight: Number(params['targetWeight']),
        duration: Number(params['duration'])
      };

    this.recommendationsData = this.recommendations.getRecommendations(this.calcData);
    });
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

}
