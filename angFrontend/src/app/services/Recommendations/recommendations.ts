import { Injectable } from '@angular/core';
import { CalcData } from '../../models/info-gym';

@Injectable({
  providedIn: 'root',
})
export class Recommendations {
  
  getRecommendations(calcData: CalcData): string[] {
  
    const plan = calcData.plan;
    const weight = calcData.weight;
    const targetWeight = calcData.targetWeight;
    const duration = calcData.duration;

    const recommendations: string[] = [];

    if(plan === 'bulk') {

      recommendations.push(
        `To reach ${targetWeight}kg in ${duration} weeks, aim for a moderate calorie surplus and consistent training.`
      );

      if(weight < 65) {

        recommendations.push(
          'Prioritize calorie-dense foods such as rice, oats, nuts and healthy fats.'
        );

        recommendations.push(
          'Focus on progressive overload in compound exercises such as squats, bench press and deadlifts.'
        );

      } else if(weight < 90) {

        recommendations.push(
          'Consume at least 1.6 - 2.2g of protein per kg of body weight daily.'
        );

        recommendations.push(
          'Aim to gain 0.25 - 0.5kg per week for lean muscle growth.'
        );

      } else {

        recommendations.push(
          'Monitor weekly weight gain carefully to minimize unnecessary fat accumulation.'
        );

        recommendations.push(
          'Keep cardio sessions in your routine to maintain cardiovascular health.'
        );
      }
    }

    if(plan === 'cut') {

      recommendations.push(
        `To reach ${targetWeight}kg in ${duration} weeks, maintain a sustainable calorie deficit.`
      );

      if(weight < 65) {

        recommendations.push(
          'Avoid aggressive calorie restriction and focus on preserving muscle mass.'
        );

      } else if(weight < 90) {

        recommendations.push(
          'Consume plenty of protein and continue resistance training during the cut.'
        );

        recommendations.push(
          'Aim to lose approximately 0.5 - 1% of body weight per week.'
        );

      } else {

        recommendations.push(
          'Increase daily activity and prioritize high-volume, low-calorie foods.'
        );

        recommendations.push(
          'Track weight trends weekly rather than focusing on daily fluctuations.'
        );
      }
    }

    if(plan === 'maintenance') {

      recommendations.push(
        'Match calorie intake to daily energy expenditure.'
      );

      recommendations.push(
        'Continue strength training to maintain muscle mass and performance.'
      );

      recommendations.push(
        'Monitor body weight weekly and adjust calories if necessary.'
      );
    }

    if(plan === 'custom') {

      recommendations.push(
        'Define a clear goal before creating a custom plan.'
      );

      recommendations.push(
        'Adjust calories, training volume and cardio based on your individual needs.'
      );

      recommendations.push(
        'Track progress consistently and make small adjustments over time.'
      );
    }

    return recommendations;
  }
}