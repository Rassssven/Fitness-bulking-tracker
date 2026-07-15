import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Product } from '../../models/product';

@Injectable({
  providedIn: 'root',
})
export class ShopService {

  private http = inject(HttpClient);
  private apiUrl = 'https://localhost:8080/products';

  getProducts() {
    return this.http.get<Product[]>(this.apiUrl);
  }

  createProduct(product: Product) {
    return this.http.post<Product>(this.apiUrl, product);
  }

  deleteProduct(prodId: number) {
    return this.http.delete(`${this.apiUrl}/${prodId}`);
  }
  
}
