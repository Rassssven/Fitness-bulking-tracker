import { Component, EventEmitter, input, Output } from '@angular/core';
import { Product } from '../../models/product';

@Component({
  selector: 'app-product-card',
  imports: [],
  templateUrl: './product-card.html',
  styleUrl: './product-card.css',
})
export class ProductCard {

  product = input.required<Product>();
  //@Input() product!: Product;

  @Output() selected = new EventEmitter<number>();

  viewProduct() {
    this.selected.emit(this.product().id);
  }

}
