import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Product } from '../../models/product';
import { ProductCard } from '../../products/product-card/product-card';
import { ProductService } from '../../products/ProductService/productService';

@Component({
  selector: 'app-shop',
  imports: [CommonModule, RouterLink, ProductCard],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop {

  products: Product[];

  constructor(private productService: ProductService) {
    this.products = this.productService.getProducts();
  }

}
