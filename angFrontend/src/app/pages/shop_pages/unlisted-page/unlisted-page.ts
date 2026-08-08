import { Component, inject, OnInit, signal } from '@angular/core';
import { ShopService } from '../../../services/HTTP/shop-service';
import { NotificationService } from '../../../shared/notification-service';
import { Product } from '../../../models/product';
import { ProductCard } from "../product-card/product-card";
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-unlisted-page',
  imports: [ProductCard, RouterLink],
  templateUrl: './unlisted-page.html',
  styleUrl: './unlisted-page.css',
})
export class UnlistedPage implements OnInit {

  private shopServ = inject(ShopService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private notifServ = inject(NotificationService);

  products = signal<Product[] | null>(null);

  search = "";

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      this.search = params['search'] ?? "";

      this.shopServ.getUnlistedProductsFiltered(this. search).subscribe(
        prods => {
          this.products.set(prods);
        }
      )
    })

    this.shopServ.getProducts().subscribe({
      next: (response) => {
        this.products.set(response);
      }
    });
  }

  goToProduct(id: number) {
    this.router.navigate(['/product-details', id]);
  }

  onSearch(event: Event) {
    const input = event.target as HTMLInputElement;

    this.search = input.value;
    this.updateQueryParams();
  }

  updateQueryParams() {

    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        search: this.search
      }

    })

  }

}
