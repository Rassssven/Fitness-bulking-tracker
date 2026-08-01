import { ProductImage } from "./DTO/ProductImageDTO";
import { ProductSpecification } from "./productSpecification";

export interface Product {
    id: number;

    name: string;

    shortDescription: string;

    description: string;

    price: number;

    images: ProductImage[];

    rating: number;

    reviews: number;

    inStock: boolean;

    category: string;

    brand: string;

    specifications: ProductSpecification[];

    listed: boolean;

    discountPercentage?: number;
}