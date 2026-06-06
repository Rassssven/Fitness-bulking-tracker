import { Goal } from "./goal";

export interface Plan {
    id: number;
    name: string;
    type: string;
    goal: Goal;
}