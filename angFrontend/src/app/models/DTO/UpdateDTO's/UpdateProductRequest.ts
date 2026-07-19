export interface UpdateProductRequest {
    name: string;
    description: string;
    shortDescription: string;
    price: number;
    inStock: boolean;
    category: string;
    brand: string;
    discountPercentage: number;
}