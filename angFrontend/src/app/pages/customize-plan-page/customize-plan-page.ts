import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CalcData } from '../../models/info-gym';
import { Exercise } from '../../models/exercise';
import { Meal } from '../../models/meal';

@Component({
  selector: 'app-customize-plan-page',
  imports: [],
  templateUrl: './customize-plan-page.html',
  styleUrl: './customize-plan-page.css',
})
export class CustomizePlanPage implements OnInit {

  showMealForm = false;
  showWorkoutForm = false;

  private route = inject(ActivatedRoute);

  calcData!: CalcData;

  meals: Meal[] = [];
  exercises: Exercise[] = [];

  ngOnInit() {
    this.calcData = history.state.data;

    if(!this.calcData) {
      console.log("No state found.")
    }

  }

}
