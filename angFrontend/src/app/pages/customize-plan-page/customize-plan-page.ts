import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CalcData } from '../../models/info-gym';

@Component({
  selector: 'app-customize-plan-page',
  imports: [],
  templateUrl: './customize-plan-page.html',
  styleUrl: './customize-plan-page.css',
})
export class CustomizePlanPage {

  private route = inject(ActivatedRoute);

  calcData!: CalcData;

  ngOnInit() {
    this.calcData = history.state.data;

    if(!this.calcData) {
      console.log("No state found.")
    }

  }

}
