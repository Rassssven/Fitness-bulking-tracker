import { Exercise } from './exercise';

export interface SavedExercise {
  id: number;
  savedAt: string;
  exercise: Exercise;
}