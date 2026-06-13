export interface CreatePlanMealRequest {
    name: string;
    calories: number;
    protein: number;
    carbs: number;
    fat: number;
    description: string;
    quantity: number;
    mealType: string;
}