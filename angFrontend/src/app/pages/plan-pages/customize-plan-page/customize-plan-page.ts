import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanExercise } from '../../../models/planExercise';
import { Plan } from '../../../models/plan';
import { PlanService } from '../../../services/HTTP/plan-service';
import { NotificationService } from '../../../shared/notification-service';
import { FormsModule } from '@angular/forms';
import { Goal } from '../../../models/goal';
import { GoalService } from '../../../services/HTTP/goal-service';
import { CommonModule } from '@angular/common';
import { FoodService } from '../../../services/HTTP/food-service';
import { PlanFood } from '../../../models/planFood';
import { ExerciseService } from '../../../services/HTTP/exercise-service';
import { CreatePlanExerciseRequest } from '../../../models/DTO/CreatePlanExerciseRequest';
import { CreatePlanMealRequest } from '../../../models/DTO/CreatePlanFoodRequest';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

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
  private exServ = inject(ExerciseService);
  private goalServ = inject(GoalService);

  planId!: number;

  plan = signal<Plan | null>(null);
  goal = signal<Goal | null>(null);
  planFoods = signal<PlanFood[]>([]);
  planExercise = signal<PlanExercise[]>([]);

  exerciseData: CreatePlanExerciseRequest = {
    name: '',
    type: '',
    caloriesPerExercise: 0,
    description: '',
    muscleGroup: '',
    sets: 0,
    reps: 0
  }

  mealData: CreatePlanMealRequest = {
    name: '',
    protein: 0,
    calories: 0,
    carbs: 0,
    fat: 0,
    description: '',
    quantity: 0,
    mealType: ''
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

      this.foodServ.getPlanFoods(this.planId).subscribe(
        foods => {
          console.log("Meals: " + foods);
          this.planFoods.set(foods);
        });

      this.exServ.getPlanExercises(this.planId).subscribe(
        exercises => {
          console.log("Exercises: " + exercises);
          this.planExercise.set(exercises);
        }
      )

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

  /* ----------------- Plan Meals -------------------------- */

  addPlanFood() {

    const mealData = {
      name: this.mealData.name,
      protein: this.mealData.protein,
      calories: this.mealData.calories,
      carbs: this.mealData.carbs,
      fat: this.mealData.fat,
      description: this.mealData.description,
      quantity: 0,
      mealType: ''
    }

    this.foodServ.createPlanFood(this.planId, mealData)
    .pipe(
      takeUntilDestroyed()
    )
    .subscribe({
      next: (response) => {
        
        this.planFoods.update(foods => [
          ...foods, response
        ]);

        this.showMealForm = false;

        this.mealData = {
          name: '',
          protein: 0,
          calories: 0,
          carbs: 0,
          fat: 0,
          description: '',
          quantity: 0,
          mealType: ''
        };

        this.notifService.showSuccess("Food added!");
      }
    });

  }

  deletePlanFood(planFoodId: number) {

    this.foodServ.deletePlanFood(planFoodId).subscribe({
      next: () => {
        console.log("Food deleted successfully");

        this.planFoods.update(foods => 
          foods.filter(food => food.id !== planFoodId)
        )

        this.notifService.showSuccess("Food deleted successfully");
      }
    })

  }

  /* ----------------- Plan Exercise -------------------------- */

  addPlanExercise() {

    const exerciseData = {
      name: this.exerciseData.name,
      type: this.exerciseData.type,
      caloriesPerExercise: this.exerciseData.caloriesPerExercise,
      description: this.exerciseData.description,
      muscleGroup: this.exerciseData.muscleGroup,
      sets: this.exerciseData.sets,
      reps: this.exerciseData.reps
    }

    this.exServ.createPlanExercise(this.planId, exerciseData).subscribe({
      next: (response) => {
        
        this.planExercise.update(exercises => [
          ...exercises, response
        ])

        this.showWorkoutForm = false;

        this.exerciseData = {
          name: '',
          type: '',
          caloriesPerExercise: 0,
          description: '',
          muscleGroup: '',
          sets: 0,
          reps: 0
        }

        this.notifService.showSuccess("Exercise added!");
      }
    });

  }

  deletePlanExercise(planExId: number) {
    this.exServ.deletePlanExercise(planExId).subscribe({
      next: () => {
        
        this.planExercise.update(exercises => 
          exercises.filter(exercises => exercises.id !== planExId)
        )

        this.notifService.showSuccess("Plan deleted successfully");
      }
    });
  }
  

}
