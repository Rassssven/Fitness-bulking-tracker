import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../auth/authService/auth.service';
import { ShopService } from '../../../services/HTTP/shop-service';
import { NotificationService } from '../../../shared/notification-service';
import { Product } from '../../../models/product';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { UpdateProductRequest } from '../../../models/DTO/UpdateDTO\'s/UpdateProductRequest';

@Component({
  selector: 'app-product-details-page',
  imports: [ReactiveFormsModule],
  templateUrl: './product-details-page.html',
  styleUrl: './product-details-page.css',
})
export class ProductDetailsPage implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router)
  private authService = inject(AuthService);
  private shopServ = inject(ShopService);
  private notifServ = inject(NotificationService);

  isOpen = false;

  updateForm = new FormGroup({
    name: new FormControl(),
    description: new FormControl(),
    shortDescription: new FormControl(),
    price: new FormControl(),
    stock: new FormControl(),
    listed: new FormControl(),
    category: new FormControl(),
    brand: new FormControl(),
    discountPercentage: new FormControl()
  })

  product = signal<Product>({
    id: 0,
    name: '',
    shortDescription: '',
    description: '',
    price: 0,
    images: [],
    rating: 0,
    reviews: 0,
    inStock: false,
    category: '',
    brand: '',
    specifications: [],
    listed: false,
    discountPercentage: 0
  });

  productId!: number;

  ngOnInit() {
    this.productId = Number(
      this.route.snapshot.paramMap.get('id')
    );

    this.shopServ.getProduct(this.productId).subscribe({
      next: (response) => {
        this.product.set(response);

        this.updateForm.patchValue({
          name: response.name,
          description: response.description,
          shortDescription: response.shortDescription,
          price: response.price,
          stock: response.inStock,
          listed: response.listed,
          category: response.category,
          brand: response.brand,
          discountPercentage: response.discountPercentage
        });
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

  updateProduct() {

    const formData: UpdateProductRequest = {
      name: this.updateForm.value.name!,
      description: this.updateForm.value.description!,
      shortDescription: this.updateForm.value.shortDescription!,
      price: this.updateForm.value.price!,
      inStock: this.updateForm.value.stock!,
      listed: this.updateForm.value.listed!,
      category: this.updateForm.value.category!,
      brand: this.updateForm.value.brand!,
      discountPercentage: this.updateForm.value.discountPercentage!
    }

    this.shopServ.updateProduct(formData, this.productId).subscribe({
      next: (response) => {

        this.product.set(response);

        this.updateForm.patchValue(response);

        this.isOpen = false;

        this.notifServ.showSuccess("Product updated!");

      }
    })
  }

  // updateProduct() {
  //   this.shopServ.updateProduct(this.productId).subscribe({
      
  //   });
  // }

}
