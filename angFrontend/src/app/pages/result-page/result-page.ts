import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CalcData } from '../../models/info-gym';

@Component({
  selector: 'app-result-page',
  imports: [],
  templateUrl: './result-page.html',
  styleUrl: './result-page.css',
})
export class ResultPage implements OnInit {

  private route = inject(ActivatedRoute);  

  calcData: CalcData = {
    age: 0,
    weight: 0,
    height: 0,
    activityLevel: '',
    plan: '',
    calories: 0
  }

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
    });
  }
  
}
