export interface CreatePlanExerciseRequest {
    name: string;
    type: string;
    caloriesPerExercise: number;
    description: string;
    muscleGroup: string;
    
    sets: number;
    reps: number;
}