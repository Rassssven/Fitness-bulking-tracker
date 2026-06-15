import { Component, inject, OnInit, signal } from '@angular/core';
import { FoodService } from '../../../services/HTTP/food-service';
import { CreateFoodRequest } from '../../../models/DTO/CreateFoodRequest';
import { FormsModule } from '@angular/forms';
import { Meal } from '../../../models/meal';
import { NotificationService } from '../../../shared/notification-service';

@Component({
  selector: 'app-food-catalog',
  imports: [FormsModule],
  templateUrl: './food-catalog.html',
  styleUrl: './food-catalog.css',
})
export class FoodCatalog implements OnInit {

  foodServ = inject(FoodService);
  notifService = inject(NotificationService)

  foods = signal<Meal[]>([]);

  foodData: CreateFoodRequest = {
    name: '',
    calories: 0,
    protein: 0,
    carbs: 0,
    fat: 0,
    description: ''
  };

  showFoodForm = false;

  ngOnInit() {

    this.foodServ.getFoods().subscribe({
      next: response => {
        this.foods.set(response);
      }
    })

  }

  createFood() {

    this.foodServ.createFood(this.foodData).subscribe({
      next: food => {
        console.log(food);
        this.notifService.showSuccess("Food added!");
      }
    });

  }


}
