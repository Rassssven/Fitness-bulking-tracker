export interface CreateExerciseRequest {
    name: string;
    type: string;
    caloriesPerExercise: number;
    description: string;
    muscleGroup: string;
}