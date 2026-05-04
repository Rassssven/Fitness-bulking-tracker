import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CalcData } from '../../models/info-gym';
import { Recommendations } from '../../services/Recommendations/recommendations';

@Component({
  selector: 'app-result-page',
  imports: [RouterLink],
  templateUrl: './result-page.html',
  styleUrl: './result-page.css',
})
export class ResultPage implements OnInit {

  private route = inject(ActivatedRoute);  
  private recommendations = inject(Recommendations);

  calcData: CalcData = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: '',
    plan: '',
    calories: 0
  }

  recommendationsData: string[] = [];

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
