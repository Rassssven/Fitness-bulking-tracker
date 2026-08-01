export interface CreateProductRequest {
    name: string;
    description: string;
    shortDescription: string;
    price: number;
    inStock: boolean;
    category: string;
    listed: boolean;
}