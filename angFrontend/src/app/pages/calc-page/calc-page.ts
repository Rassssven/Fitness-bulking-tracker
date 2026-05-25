import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CalcData } from '../../models/info-gym';
import { PlanService } from '../../services/HTTP/plan-service';
import { NotificationService } from '../../shared/notification-service';
import { Plan } from '../../models/plan';

@Component({
  selector: 'app-calc-page',
  imports: [FormsModule],
  templateUrl: './calc-page.html',
  styleUrl: './calc-page.css',
})
export class CalcPage implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private planService = inject(PlanService);
  private notifService = inject(NotificationService);

  calcData: CalcData = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: 'sedentary',
    plan: 'custom',
    calories: 0
  }

  planData: Plan = {
    name: 'MyPlan',
    type: ''
  }

  savePlan() {
    this.planData.type = this.calcData.plan;

    this.planService.createPlan(this.planData).subscribe({

      next: (response) => {
        console.log('Plan saved successfully:', response);
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
      const plan = params['plan'];

      if (plan) {
        this.calcData.plan = plan;
      }
    });
  }

  goToResult() {
    this.router.navigate(['/result-page'], { queryParams: { 
      age: this.calcData.age,
      weight: this.calcData.weight,
      height: this.calcData.height,
      activityLevel: this.calcData.activityLevel,
      plan: this.calcData.plan,
      calories: this.calcData.calories
     } 
    });
  }

}
