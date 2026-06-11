import { Meal } from "./meal";

export interface PlanFood {
    id: number;
    quantity: number;
    mealType: string;
    food: Meal;
}