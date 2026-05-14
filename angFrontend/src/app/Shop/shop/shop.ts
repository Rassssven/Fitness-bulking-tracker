import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../../models/product';

@Component({
  selector: 'app-shop',
  imports: [CommonModule],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop {

  products: Product[] = [
    {
      id: 1,
      name: 'Whey Protein',
      price: 59,
      description: 'High quality whey protein for muscle growth and recovery.',
      image: '/products/whey.jpg'
    },

    {
      id: 2,
      name: 'Creatine Monohydrate',
      price: 24,
      description: 'Increase strength, power, and workout performance.',
      image: '/products/creatine.jpg'
    },

    {
      id: 3,
      name: 'Pre Workout',
      price: 39,
      description: 'Energy boost supplement for intense training sessions.',
      image: '/products/preworkout.jpg'
    },

    {
      id: 4,
      name: 'Omega 3',
      price: 19,
      description: 'Supports heart health and joint recovery.',
      image: '/products/omega3.jpg'
    },

    {
      id: 5,
      name: 'Mass Gainer',
      price: 74,
      description: 'Calorie-dense shake designed for clean bulking.',
      image: '/products/massgainer.jpg'
    }
  ];

  private router = inject(Router);

  goToProduct(id: number) {
    this.router.navigate(['/product', id]);
  }

}
