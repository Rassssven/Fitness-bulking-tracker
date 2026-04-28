import { Component, inject } from '@angular/core';
import { GridModule } from '@progress/kendo-angular-grid';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { CardModule, GridLayoutModule } from '@progress/kendo-angular-layout';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [    
    GridModule,
    ButtonsModule,
    GridLayoutModule,
    CardModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  gymOptions = [
    {
      title: 'Clean Bulk',
      key: 'bulk',
      description: 'Increase muscle mass with clean calories',
      calories: '2800 – 3200 kcal',
      difficulty: 'Medium'
    },
    {
      title: 'Cut',
      key: 'cut',
      description: 'Lose fat while preserving muscle',
      calories: '1800 – 2200 kcal',
      difficulty: 'Hard'
    },
    {
      title: 'Maintenance',
      key: 'maintenance',
      description: 'Maintain current physique',
      calories: '2400 – 2600 kcal',
      difficulty: 'Easy'
    },
    {
      title: 'Custom Plan',
      key: 'custom',
      description: 'Personalized nutrition strategy',
      calories: 'Calculated individually',
      difficulty: 'Custom'
    }
  ];

  private router = inject(Router);

  goToCalc(plan: string) {
    this.router.navigate(['/calc-page'], { queryParams: { plan } });
  }


}
