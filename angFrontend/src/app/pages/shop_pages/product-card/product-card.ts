import { Component, EventEmitter, inject, input, Output } from '@angular/core';
import { CartService } from '../../../services/CartService';
import { Product } from '../../../models/product';

@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.html',
  styleUrl: './product-card.css',
})
export class ProductCard {

  cartServ = inject(CartService);

  isAdminView = input(false);

  product = input.required<Product>();
  //@Input() product!: Product;

  @Output() selected = new EventEmitter<number>();

  viewProduct() {
    console.log("VIEW PRODUCT");
    this.selected.emit(this.product().id);
  }

  // addToCart(prod: Product) {
  //   this.cartServ.addProduct();
  // }

}
