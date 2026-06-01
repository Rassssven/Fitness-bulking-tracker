import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CalcData } from '../../models/info-gym';
import { Recommendations } from '../../services/Recommendations/recommendations';
import { NotificationService } from '../../shared/notification-service';
import { PlanService } from '../../services/HTTP/plan-service';
import { AuthService } from '../../auth/authService/auth.service';
import { UserService } from '../../services/HTTP/user-service';

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
  private userService = inject(UserService);
  private authService = inject(AuthService);

  calcData: CalcData = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: '',
    gender: '',
    plan: '',
    calories: 0
  }

  recommendationsData: string[] = [];

  planData = {
    name: 'MyPlan',
    type: this.calcData.plan
  }
  
  savePlan() {
    this.planData.type = this.calcData.plan;
    console.log(this.planData);
  
    this.planService.createPlan(this.planData).subscribe({
  
      next: (response) => {
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
        age: Number(params['age']),
        weight: Number(params['weight']),
        height: Number(params['height']),
        gender: params['gender'],
        activityLevel: params['activityLevel'],
        plan: params['plan'],
        calories: Number(params['calories'])
      };

    this.recommendationsData = this.recommendations.getRecommendations(this.calcData);
    });
  }

  updateUser() {

    const updatedUserData = {
      age: this.calcData.age,
      gender: this.calcData.gender,
      height: this.calcData.height,
      activityLevel: this.calcData.activityLevel
    };

    this.userService.updateUser(updatedUserData).subscribe({
      next: () => {
        this.notifService.showSuccess("User data updated successfully");
      },
      error: () => {
        this.notifService.showError("Error updating user data");
      }
    });

  }

}
