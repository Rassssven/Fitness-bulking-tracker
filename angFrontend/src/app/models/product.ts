import { ProductSpecification } from "./productSpecification";

export interface Product {
    id: number;

    name: string;

    shortDescription: string;

    description: string;

    price: number;

    images: string[];

    rating: number;

    reviews: number;

    inStock: boolean;

    category: string;

    brand: string;

    specifications: ProductSpecification[];

    discountPercentage?: number;
}