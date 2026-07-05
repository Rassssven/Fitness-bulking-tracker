export interface ProductShop {
    id: number,
    name: string,
    price: number,
    shortDescription: string,
    image?: string,
    rating?: number,
    reviews?: number,
    inStock?: boolean
}