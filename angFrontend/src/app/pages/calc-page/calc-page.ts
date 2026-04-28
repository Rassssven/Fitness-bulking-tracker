import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-calc-page',
  imports: [FormsModule],
  templateUrl: './calc-page.html',
  styleUrl: './calc-page.css',
})
export class CalcPage implements OnInit {

  private route = inject(ActivatedRoute);

  selectedPlan = 'custom';

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const plan = params['plan'];

      if (plan) {
        this.selectedPlan = plan;
      }
    });
  }

}
