import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { ProductCard } from "../product-card/product-card";
import { ProductShop } from '../../models/productShop';
import { ShopService } from '../../services/HTTP/shop-service';
import { NotificationService } from '../../shared/notification-service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-shop',
  imports: [CommonModule, ProductCard],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop implements OnInit {

  products: ProductShop[] = [
    {
      id: 1,
      name: 'Whey Protein',
      price: 59,
      shortDescription: 'High quality whey protein for muscle growth and recovery.',
      image: '/products/whey.jpg'
    },

    {
      id: 2,
      name: 'Creatine Monohydrate',
      price: 24,
      shortDescription: 'Increase strength, power, and workout performance.',
      image: '/products/creatine.jpg'
    },

    {
      id: 3,
      name: 'Pre Workout',
      price: 39,
      shortDescription: 'Energy boost supplement for intense training sessions.',
      image: '/products/preworkout.jpg'
    },

    {
      id: 4,
      name: 'Omega 3',
      price: 19,
      shortDescription: 'Supports heart health and joint recovery.',
      image: '/products/omega3.jpg'
    },

    {
      id: 5,
      name: 'Mass Gainer',
      price: 74,
      shortDescription: 'Calorie-dense shake designed for clean bulking.',
      image: '/products/massgainer.jpg'
    },

    {
      id: 6,
      name: 'Omega 3',
      price: 19,
      shortDescription: 'Supports heart health and joint recovery.',
      image: '/products/omega3.jpg'
    },

    {
      id: 7,
      name: 'Mass Gainer',
      price: 74,
      shortDescription: 'Calorie-dense shake designed for clean bulking.',
      image: '/products/massgainer.jpg'
    }

  ];

  productss = signal<Product[]>([]);

  private router = inject(Router);
  private shopServ = inject(ShopService);
  private notifServ = inject(NotificationService);

  ngOnInit() {

    this.shopServ.getProducts().subscribe({
      next: (response) => {
        this.productss.set(response);
      }
    });

  }

  
  goToProduct(id: number) {
    this.router.navigate(['/product-details', id]);
  }



}
