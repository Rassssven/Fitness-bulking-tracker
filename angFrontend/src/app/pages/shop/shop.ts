import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Product } from '../../models/product';

@Component({
  selector: 'app-shop',
  imports: [CommonModule, RouterLink],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop {

  products: Product[] = [
    {
      id: 1,
      name: 'Laptop',
      price: 4050,
      description: 'Powerful laptop'
    }, 
    {
      id: 2,
      name: 'Phone',
      price: 2400,
      description: 'Smart phone'
    }
  ]
}
