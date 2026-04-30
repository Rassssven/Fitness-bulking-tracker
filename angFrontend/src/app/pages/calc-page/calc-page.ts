import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-calc-page',
  imports: [FormsModule],
  templateUrl: './calc-page.html',
  styleUrl: './calc-page.css',
})
export class CalcPage implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router);

  selectedPlan = 'custom';

  calcData = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: 'sedentary',
    plan: this.selectedPlan,
    calories: 0
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const plan = params['plan'];

      if (plan) {
        this.selectedPlan = plan;
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
