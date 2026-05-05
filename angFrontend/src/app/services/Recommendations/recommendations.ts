import { Injectable } from '@angular/core';
import { CalcData } from '../../models/info-gym';

@Injectable({
  providedIn: 'root',
})
export class Recommendations {
  
  getRecommendations(calcData: CalcData): string[] {
  
    const plan = calcData.plan;
    //const calories = calcData.calories;
    //const activity = calcData.activityLevel;

    const age = calcData.age;
    const weight = calcData.weight;
    const height = calcData.height;

    const recommendations: string[] = [];

    if(plan === 'bulk') {
      
      if(age < 18) {
        recommendations.push('As you are under 18, it is recommended to focus on a balanced diet and regular exercise rather than bulking.');
      } else {
        recommendations.push('To bulk effectively, aim for a calorie surplus of 250-500 calories per day.');
      }

      if(weight < 65) {
        recommendations.push('Focus on consuming nutrient-dense foods to support muscle growth.');
      } else if (weight > 65 && weight < 90) {
        recommendations.push('Consider incorporating more protein into your diet to support muscle development.');
      } else {
        recommendations.push('Monitor your calorie intake closely to avoid excessive fat gain while bulking.');
      }

      if(height < 160) {
        recommendations.push('Incorporate strength training exercises to maximize muscle growth.');
      } else {
        recommendations.push('Ensure you are getting enough rest and recovery to support muscle growth.');
      }

    }

    if(plan === 'cut') {

      if(age < 18) {
        recommendations.push('As you are under 18, it is recommended to focus on a balanced diet and regular exercise rather than cutting.');
      } else {
        recommendations.push('To cut effectively, aim for a calorie deficit of 500-750 calories per day.');
      }

      if(weight < 65) {
        recommendations.push('Focus on consuming nutrient-dense foods to support fat loss while maintaining muscle mass.');
      } else if (weight > 65 && weight < 90) {
        recommendations.push('Consider incorporating more protein into your diet to support muscle retention while cutting.');
      } else {
        recommendations.push('Monitor your calorie intake closely to ensure you are in a calorie deficit while cutting.');
      }

      if(height < 160) {
        recommendations.push('Incorporate a mix of cardio and strength training exercises to maximize fat loss while maintaining muscle mass.');
      } else {
        recommendations.push('Ensure you are getting enough rest and recovery to support fat loss while cutting.');
      }
    }
    
    if(plan === 'maintenance') {

        recommendations.push('To maintain your current weight, aim to consume the same number of calories as you burn each day.');  
      
      }

    if(plan === 'custom') {
      recommendations.push('For a custom plan, it is recommended to consult with a nutritionist or fitness professional to create a personalized plan based on your specific goals and needs.');
    }

    return recommendations;
  }
}