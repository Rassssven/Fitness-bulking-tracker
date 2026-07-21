import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CalcData } from '../../../models/info-gym';
import { PlanService } from '../../../services/HTTP/plan-service';
import { NotificationService } from '../../../shared/notification-service';

@Component({
  selector: 'app-calc-page',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './calc-page.html',
  styleUrl: './calc-page.css',
})
export class CalcPage implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private planService = inject(PlanService);
  private notifService = inject(NotificationService);

  // age = new FormControl('');

  // calorieForm = new FormGroup({
  //   weight: new FormControl('', Validators.required),
  //   targetWeight: new FormControl('', [Validators.required, Validators.min(80)]),
  //   duration: new FormControl('', Validators.required),
  // })

  // fb = inject(FormBuilder);

  // form = this.fb.group({
  //   weight: ['', Validators.required],
  //   targetWeight: ['', Validators.required],
  //   duration: ['', Validators.required]
  // })


  calcData: CalcData = {
    plan: 'custom',
    weight: 0,
    targetWeight: 0,
    duration: 0
  }
  
  notifForm() {

    this.notifService.showSuccess('From invalid!');

  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const planType = params['planType'];

      if (planType) {
        this.calcData.plan = planType;
      }
    });
  }

  goToResult() {
    this.router.navigate(['/result-page'], { queryParams: { 
      weight: this.calcData.weight,
      plan: this.calcData.plan,
      targetWeight: this.calcData.targetWeight,
      duration: this.calcData.duration
     } 
    });
  }

}
