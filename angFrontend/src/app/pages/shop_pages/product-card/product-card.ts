import { Component, EventEmitter, inject, input, Output } from '@angular/core';
import { CartService } from '../../../services/CartService';
import { ProductShop } from '../../../models/productShop';

@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.html',
  styleUrl: './product-card.css',
})
export class ProductCard {

  cartServ = inject(CartService);

  product = input.required<ProductShop>();
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
