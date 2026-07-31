import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../auth/authService/auth.service';
import { ShopService } from '../../../services/HTTP/shop-service';
import { NotificationService } from '../../../shared/notification-service';
import { Product } from '../../../models/product';

@Component({
  selector: 'app-product-details-page',
  imports: [],
  templateUrl: './product-details-page.html',
  styleUrl: './product-details-page.css',
})
export class ProductDetailsPage implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router)
  private authService = inject(AuthService);
  private shopServ = inject(ShopService);
  private notifServ = inject(NotificationService);

  product = signal<Product | null>(null);

  productId!: number;

  ngOnInit() {
    this.productId = Number(
      this.route.snapshot.paramMap.get('id')
    );

    this.shopServ.getProduct(this.productId).subscribe({
      next: (response) => {
        this.product.set(response);
      }
    })
  }

  isAdmin() {
    return this.authService.getCurrentUser()?.role === 'ADMIN';
  }

  deleteProduct() {
    this.shopServ.deleteProduct(this.productId).subscribe({
      next: () => {
        console.log("Product deleted.");

        this.notifServ.showSuccess("Product has been deleted.")

        this.router.navigate(['/shop']);
      }
    })
  }

  // updateProduct() {
  //   this.shopServ.updateProduct(this.productId).subscribe({
      
  //   });
  // }

}
