import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Exercise } from '../../models/exercise';
import { Meal } from '../../models/meal';
import { Plan } from '../../models/plan';
import { PlanService } from '../../services/HTTP/plan-service';
import { NotificationService } from '../../shared/notification-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customize-plan-page',
  imports: [FormsModule],
  templateUrl: './customize-plan-page.html',
  styleUrl: './customize-plan-page.css',
})
export class CustomizePlanPage implements OnInit {

  showMealForm = false;
  showWorkoutForm = false;
  updatePlanForm = false;

  updateName = '';
  updateType = '';

  private route2 = inject(Router);
  private route = inject(ActivatedRoute);
  private notifService = inject(NotificationService);
  private planServ = inject(PlanService);

  meals: Meal[] = [];
  exercises: Exercise[] = [];

  plan = signal<Plan | null>(null);
  

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      const planId = Number(params.get('id'));
      console.log(params);

      this.planServ.getPlanById(planId).subscribe(
        plan => {
          this.plan.set(plan);
        }
      );
    });
  }

  deletePlan() {

    const plan = this.plan();
    if(!plan) { return };

    this.planServ.deletePlan(plan.id).subscribe({
    
      next: () => {
        console.log("Plan deleted successfully");

        this.route2.navigate(['/profile-dashboard']);

        this.notifService.showSuccess("Plan deleted successfully");
      }
    });
  }

  updatePlan() {

    const plan = this.plan();
    if(!plan) { return };
      
    const updatedPlan = {
      name: this.updateName,
      type: this.updateType
    };

    this.planServ.updatePlan(plan.id, updatedPlan).subscribe({
      next: (updatedPlan) => {
        this.plan.set(updatedPlan);

        this.notifService.showSuccess("Plan updated successfully");

        this.updatePlanForm = false;
      }
    });
    
  }

}
