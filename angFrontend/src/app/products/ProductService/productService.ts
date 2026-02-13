import { Injectable } from "@angular/core";
import { Product } from "../../models/product";

@Injectable({providedIn: 'root'})
export class ProductService {
    
    products: Product[] = [
        {
          id: 1,
          name: 'Laptop',
          price: 4050,
          description: 'Powerful laptop',
          image: 'product1.jpg'
        }, 
        {
          id: 2,
          name: 'Phone',
          price: 2400,
          description: 'Smart phone',
          image: 'product1.jpg'
        },
        {
          id: 3,
          name: 'Laptop',
          price: 4050,
          description: 'Powerful laptop',
          image: 'product1.jpg'
        }, 
        {
          id: 4,
          name: 'Phone',
          price: 2400,
          description: 'Smart phone',
          image: 'product1.jpg'
        },
        {
          id: 5,
          name: 'Laptop',
          price: 4050,
          description: 'Powerful laptop',
          image: 'product1.jpg'
        }, 
        {
          id: 6,
          name: 'Phone',
          price: 2400,
          description: 'Smart phone',
          image: 'product1.jpg'
        }
      ]

    getProducts(): Product[] {
        return this.products;
    }

    getProductById(id: number): Product | undefined{
        return this.products.find(p => p.id === id);
    }
      
}