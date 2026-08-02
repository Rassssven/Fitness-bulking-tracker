import { DailyTrackerFood } from './dailyTrackerFood';

export interface DailyTracker {
  id: number;
  date: string;

  totalCalories: number;
  totalProtein: number;
  totalCarbs: number;
  totalFat: number;

  meals: DailyTrackerFood[];
}
