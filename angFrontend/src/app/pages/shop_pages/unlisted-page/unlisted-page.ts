import { Component, inject, OnInit, signal } from '@angular/core';
import { ShopService } from '../../../services/HTTP/shop-service';
import { NotificationService } from '../../../shared/notification-service';
import { Product } from '../../../models/product';
import { ProductCard } from "../product-card/product-card";
import { Router } from '@angular/router';

@Component({
  selector: 'app-unlisted-page',
  imports: [ProductCard],
  templateUrl: './unlisted-page.html',
  styleUrl: './unlisted-page.css',
})
export class UnlistedPage implements OnInit {

  private shopServ = inject(ShopService);
  private router = inject(Router);
  private notifServ = inject(NotificationService);

  products = signal<Product[] | null>(null);

  ngOnInit() {
    this.shopServ.getProducts().subscribe({
      next: (response) => {
        this.products.set(response);
      }
    });
  }

  goToProduct(id: number) {
    this.router.navigate(['/product-details', id]);
  }

}
