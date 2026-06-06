import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'planTypePipe',
})
export class PlanTypePipePipe implements PipeTransform {

  transform(value: unknown): unknown {
    
      switch(value) {

        case 'BULK':
          return 'Muscle Gain';

        case 'CUT':
          return 'Fat loss';

        case 'MAINTENANCE':
          return 'Weight Maintenance'

        default:
          return value;
      }

  }

}
