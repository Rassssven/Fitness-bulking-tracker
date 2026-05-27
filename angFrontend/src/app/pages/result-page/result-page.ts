import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CalcData } from '../../models/info-gym';
import { Recommendations } from '../../services/Recommendations/recommendations';
import { Plan } from '../../models/plan';
import { NotificationService } from '../../shared/notification-service';
import { PlanService } from '../../services/HTTP/plan-service';
import { AuthService } from '../../auth/authService/auth.service';

@Component({
  selector: 'app-result-page',
  imports: [RouterLink],
  templateUrl: './result-page.html',
  styleUrl: './result-page.css',
})
export class ResultPage implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);  
  private recommendations = inject(Recommendations);
  private planService = inject(PlanService);
  private notifService = inject(NotificationService);
  private authService = inject(AuthService);

  calcData: CalcData = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: '',
    plan: '',
    calories: 0
  }

  recommendationsData: string[] = [];

  planData: Plan = {
    id: 0,
    name: 'MyPlan',
    type: ''
  }
  
  savePlan() {
    this.planData.type = this.calcData.plan;
  
    this.planService.createPlan(this.planData).subscribe({
  
      next: (response) => {
        console.log('Plan saved successfully:', response);

        this.router.navigate(['/customize-plan-page'], {state: {data: this.calcData}});

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
        age: Number(params['age']),
        weight: Number(params['weight']),
        height: Number(params['height']),
        activityLevel: params['activityLevel'],
        plan: params['plan'],
        calories: Number(params['calories'])
      };

    this.recommendationsData = this.recommendations.getRecommendations(this.calcData);
    });
  }

}
