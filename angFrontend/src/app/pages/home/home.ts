import { Component } from '@angular/core';
import { GridModule } from '@progress/kendo-angular-grid';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { CardModule, GridLayoutModule } from '@progress/kendo-angular-layout';

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
      description: 'Increase muscle mass with clean calories',
      calories: '2800 – 3200 kcal',
      difficulty: 'Medium'
    },
    {
      title: 'Cut',
      description: 'Lose fat while preserving muscle',
      calories: '1800 – 2200 kcal',
      difficulty: 'Hard'
    },
    {
      title: 'Maintenance',
      description: 'Maintain current physique',
      calories: '2400 – 2600 kcal',
      difficulty: 'Easy'
    },
    {
      title: 'Custom Plan',
      description: 'Personalized nutrition strategy',
      calories: 'Calculated individually',
      difficulty: 'Custom'
    }
  ];


}
