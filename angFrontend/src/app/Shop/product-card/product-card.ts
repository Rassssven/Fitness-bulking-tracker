import { Component, EventEmitter, inject, input, Output } from '@angular/core';
import { Product } from '../../models/product';
import { CartService } from '../../services/CartService';

@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.html',
  styleUrl: './product-card.css',
})
export class ProductCard {

  cartServ = inject(CartService);

  product = input.required<Product>();
  //@Input() product!: Product;

  @Output() selected = new EventEmitter<number>();

  viewProduct() {
    this.selected.emit(this.product().id);
  }

  // addToCart(prod: Product) {
  //   this.cartServ.addProduct();
  // }

}
