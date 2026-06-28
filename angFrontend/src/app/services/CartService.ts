import { Injectable, signal } from '@angular/core';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

    cartItems = signal<Product[]>([]);

    addProduct(prod: Product) {
        this.cartItems.update(products => [
            ...products,
            prod
        ])
    }

    getProducts() {
        return this.cartItems();
    } // nu mai e nevoie, putem expune prin @for

    removeProduct(id: number) {
        this.cartItems.update(products =>
            products.filter(p => p.id !== id)
        )
    }

    clearCart() {
        this.cartItems.set([]);
    }

    getTotal() {
        return this.cartItems().reduce((sum, p) => sum + p.price, 0);
    }

    /* Array */

    // cartItemsA: Product[] = [];

    // addToCart(prod: Product) {
    //     this.cartItemsA.push(prod);
    // }

    // getItem() {
    //     return this.cartItemsA;
    // }

    // removeItem(id: number) {
    //     this.cartItemsA = this.cartItemsA.filter(
    //         p => p.id !== id
    //     )
    // }

    // clear() {
    //     this.cartItemsA = [];
    // }

    // getTotalA() {
    //     return this.cartItemsA.reduce(
    //         (sum, p) => sum + p.price, 0
    //     );
    // }

}