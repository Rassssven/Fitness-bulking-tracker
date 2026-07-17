export interface CreateProductRequest {
    name: string;
    description: string;
    price: number;
    inStock: boolean;
    category: string;
    images: string[];
}