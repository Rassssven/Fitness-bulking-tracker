import { Meal } from './meal';

export interface SavedFood {
  id: number;
  savedAt: string;
  food: Meal;
}