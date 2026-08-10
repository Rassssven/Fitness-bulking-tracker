import { Component, inject, OnInit, signal } from '@angular/core';
import { FoodService } from '../../../services/HTTP/food-service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Meal } from '../../../models/meal';
import { NotificationService } from '../../../shared/notification-service';
import { CreateFoodRequest } from '../../../models/DTO/CreateFoodRequest';
import { AuthService } from '../../../auth/authService/auth.service';

@Component({
  selector: 'app-food-catalog',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './food-catalog.html',
  styleUrl: './food-catalog.css',
})
export class FoodCatalog implements OnInit {

  foodServ = inject(FoodService);
  notifService = inject(NotificationService)
  private auth = inject(AuthService);

  foods = signal<Meal[]>([]);

  foodForm = new FormGroup({

    name: new FormControl('', [
      Validators.required,
      Validators.minLength(3)
    ]),

    calories: new FormControl(0, [
      Validators.required
    ]),

    protein: new FormControl(0, [
      Validators.required
    ]),

    carbs: new FormControl(0, [
      Validators.required
    ]),

    fat: new FormControl(0),

    description: new FormControl('')

  });

  showFoodForm = false;

  ngOnInit() {

    this.foodServ.getFoods().subscribe({
      next: response => {
        this.foods.set(response);
      }
    })

  }

  isAdmin() {
    return this.auth.getCurrentUser()?.role === 'ADMIN';
  }

  createFood() {

    if(this.foodForm.invalid) {
      return;
    }

    const dto: CreateFoodRequest = {
      name: this.foodForm.value.name ?? '',
      calories: this.foodForm.value.calories ?? 0,
      protein: this.foodForm.value.protein ?? 0,
      carbs: this.foodForm.value.carbs ?? 0,
      fat: this.foodForm.value.fat ?? 0,
      description: this.foodForm.value.description ?? ''
    }

    this.foodServ.createFood(dto).subscribe({
      next: food => {
        
        this.foods.update(foods => [
          ...foods, food
        ]);

        this.foodForm.reset({
          calories: 0,
          protein: 0,
          carbs: 0,
          fat: 0
        });

        this.showFoodForm = false;

        this.notifService.showSuccess("Food added!");
      }
    });

  }

  deleteFood(foodId: number) {

    this.foodServ.deleteFood(foodId).subscribe({
      next: () => {
        console.log("Food deleted")

        this.foods.update(foods => 
          foods.filter(food => food.id !== foodId)
        )

        this.notifService.showSuccess("Food deleted!");
      }

    })

  }


}
