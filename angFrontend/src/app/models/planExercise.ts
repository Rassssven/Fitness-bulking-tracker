import { Exercise } from "./exercise";

export interface PlanExercise {
    id: number;
    sets: number;
    reps: number;
    exercise: Exercise;
}