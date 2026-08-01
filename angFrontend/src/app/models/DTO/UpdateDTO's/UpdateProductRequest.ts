export interface UpdateProductRequest {
    name: string;
    description: string;
    shortDescription: string;
    price: number;
    inStock: boolean;
    listed: boolean;
    category: string;
    brand: string;
    discountPercentage: number;
}