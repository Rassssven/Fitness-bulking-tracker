import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProductCard } from "../product-card/product-card";
import { ShopService } from '../../../services/HTTP/shop-service';
import { NotificationService } from '../../../shared/notification-service';
import { Product } from '../../../models/product';
import { AuthService } from '../../../auth/authService/auth.service';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CreateProductRequest } from '../../../models/DTO/CreateProductRequest';

@Component({
  selector: 'app-shop',
  imports: [CommonModule, ProductCard, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop implements OnInit {

  // products: ProductShop[] = [
  //   {
  //     id: 1,
  //     name: 'Whey Protein',
  //     price: 59,
  //     shortDescription: 'High quality whey protein for muscle growth and recovery.',
  //     image: '/products/whey.jpg'
  //   },

  //   {
  //     id: 2,
  //     name: 'Creatine Monohydrate',
  //     price: 24,
  //     shortDescription: 'Increase strength, power, and workout performance.',
  //     image: '/products/creatine.jpg'
  //   },

  //   {
  //     id: 3,
  //     name: 'Pre Workout',
  //     price: 39,
  //     shortDescription: 'Energy boost supplement for intense training sessions.',
  //     image: '/products/preworkout.jpg'
  //   },

  //   {
  //     id: 4,
  //     name: 'Omega 3',
  //     price: 19,
  //     shortDescription: 'Supports heart health and joint recovery.',
  //     image: '/products/omega3.jpg'
  //   },

  //   {
  //     id: 5,
  //     name: 'Mass Gainer',
  //     price: 74,
  //     shortDescription: 'Calorie-dense shake designed for clean bulking.',
  //     image: '/products/massgainer.jpg'
  //   },

  //   {
  //     id: 6,
  //     name: 'Omega 3',
  //     price: 19,
  //     shortDescription: 'Supports heart health and joint recovery.',
  //     image: '/products/omega3.jpg'
  //   },

  //   {
  //     id: 7,
  //     name: 'Mass Gainer',
  //     price: 74,
  //     shortDescription: 'Calorie-dense shake designed for clean bulking.',
  //     image: '/products/massgainer.jpg'
  //   }

  // ];

  productss = signal<Product[]>([]);

  search = "";
  category = "";
  sort = "";

  isOpen = false;

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private shopServ = inject(ShopService);
  private notifServ = inject(NotificationService);
  private auth = inject(AuthService);
  private fb = inject(FormBuilder);

  /* -- Form -- */

  shopForm = this.fb.group({
    name: ['', [
      Validators.required
    ]],
    description: [''],
    shortDescription: [''],
    price: [0, [
      Validators.required
    ]],
    stock: [true],
    listed: [true],
    category: [''],
  })

  // get images(): FormArray {
  //   return this.shopForm.get('images') as FormArray;
  // }

  // createImage() {
  //   return this.fb.control('');
  // }

  // addImages() {
  //   this.images.push(
  //     this.fb.control('')
  //   )
  // }

  // removeImage(index: number) {
  //   this.images.removeAt(index);
  // }

  isAdmin() {
    return this.auth.getCurrentUser()?.role === 'ADMIN';
  }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      this.search = params['search'] ?? "";
      this.category = params['category'] ?? "";
      this.sort = params['sort'] ?? "";

      this.loadProducts();
    })

    this.shopServ.getListedProducts().subscribe({
      next: (response) => {
        this.productss.set(response);
      }
    });

  }

  createProduct() {

    const productData: CreateProductRequest = {
      name: this.shopForm.value.name!,
      description: this.shopForm.value.description!,
      shortDescription: this.shopForm.value.shortDescription!,
      price: this.shopForm.value.price!,
      inStock: this.shopForm.value.stock!,
      listed: this.shopForm.value.listed!,
      category: this.shopForm.value.category!,
    }

    this.shopServ.createProduct(productData).subscribe({
      next: (response) => {

        this.productss.update(prods => [
          ...prods, response
        ])

        if (this.selectedFile.length > 0) {
          this.shopServ.uploadImages(response.id, this.selectedFile)
            .subscribe({
              next: (updatedProduct) => {

                this.productss.update(prods =>
                  prods.map(p => p.id === updatedProduct.id ? updatedProduct : p)
                );

              }
            })
        }

        this.isOpen = false;
      }
    })


  }

  goToProduct(id: number) {
    this.router.navigate(['/product-details', id]);
  }

  /* -- File -- */

  selectedFile: File[] = [];

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;

    if(!input.files || input.files.length === 0) {
      this.selectedFile = [];
      return;
    }

    this.selectedFile = Array.from(input.files);
  }

  /* Query Params */

  onSearch(event: Event) {
    const input = event.target as HTMLInputElement;

    this.search = input.value;
    this.updateQueryParams();
  }

  onCategoryChange(event: Event) {
    const select = event.target as HTMLSelectElement;

    this.category = select.value;
    this.updateQueryParams();
  }

  onSortChange(event: Event) {
    const select = event.target as HTMLSelectElement;

    this.sort = select.value;
    this.updateQueryParams();
  }

  updateQueryParams() {

    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        search: this.search,
        category: this.category,
        sort: this.sort
      },
      
      queryParamsHandling:'merge'
    })

  }

  loadProducts() {
    this.shopServ.getProductsFiltered(this.search, this.category, this.sort)
    .subscribe(prods => {
      this.productss.set(prods);
    })
  }

}
