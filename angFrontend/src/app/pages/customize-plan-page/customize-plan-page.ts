import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Exercise } from '../../models/exercise';
import { Meal } from '../../models/meal';
import { Plan } from '../../models/plan';
import { PlanService } from '../../services/HTTP/plan-service';
import { NotificationService } from '../../shared/notification-service';
import { FormsModule } from '@angular/forms';
import { Goal } from '../../models/goal';
import { GoalService } from '../../services/HTTP/goal-service';
import { CommonModule } from '@angular/common';
import { FoodService } from '../../services/HTTP/food-service';
import { PlanFood } from '../../models/planFood';

@Component({
  selector: 'app-customize-plan-page',
  imports: [FormsModule, CommonModule],
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
  private foodServ = inject(FoodService);
  private goalServ = inject(GoalService);

  exercises: Exercise[] = [];

  planId!: number;

  plan = signal<Plan | null>(null);
  goal = signal<Goal | null>(null);
  planFoods = signal<PlanFood[]>([]);

  mealData: Meal = {
    name: '',
    protein: 0,
    calories: 0,
    carbs: 0,
    fat: 0,
    description: ''
  }
  
  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.planId = Number(params.get('id'));

      this.planServ.getPlanById(this.planId).subscribe(
        plan => {
          console.log(plan);
          this.plan.set(plan);
          this.goal.set(plan.goal);
        });

      this.foodServ.getFoods(this.planId).subscribe(
        foods => {
          console.log(foods);
          this.planFoods.set(foods);
        });

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

    this.planServ.updatePlan(plan.id, updatedPlan).subscribe({ //Ce trimitem
      next: (updatedPlan) => { //Datele pe care le primim
        this.plan.set(updatedPlan);

        this.notifService.showSuccess("Plan updated successfully");

        this.updatePlanForm = false;
      }
    });
    
  }

  /* ----------------- Meals -------------------------- */

  addPlanFood() {

    const mealData = {
      name: this.mealData.name,
      protein: this.mealData.protein,
      calories: this.mealData.calories,
      carbs: this.mealData.carbs,
      fat: this.mealData.fat,
      description: this.mealData.description
    }

    this.foodServ.createPlanFood(this.planId, mealData).subscribe({
      next: (response) => {
        console.log(response);
        this.notifService.showSuccess("Food added!");
      }
    });

  }

  deletePlanFood(planFoodId: number) {

    this.foodServ.deleteFood(planFoodId).subscribe({
      next: () => {
        console.log("Food deleted successfully");
        this.notifService.showSuccess("Food deleted successfully");
      }
    })

  }

  /* ----------------- Exercise -------------------------- */

  

}
