import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Product } from '../../models/product';
import { ProductCard } from '../product-card/product-card';

@Component({
  selector: 'app-shop',
  imports: [CommonModule, RouterLink, ProductCard],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop {

  searchText = '';

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
    }
  ]

  onBuy(product: Product) {
    alert(`Ai cumparat ${product.name}`);
  }
}
