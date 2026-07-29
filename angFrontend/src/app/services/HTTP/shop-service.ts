import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Product } from '../../models/product';
import { CreateProductRequest } from '../../models/DTO/CreateProductRequest';
import { UpdateProductRequest } from '../../models/DTO/UpdateDTO\'s/UpdateProductRequest';

@Injectable({
  providedIn: 'root',
})
export class ShopService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/products';

  getProducts() {
    return this.http.get<Product[]>(this.apiUrl);
  }

  createProduct(product: CreateProductRequest) {
    return this.http.post<Product>(this.apiUrl, product);
  }

  deleteProduct(prodId: number) {
    return this.http.delete(`${this.apiUrl}/${prodId}`);
  }

  updateProduct(prodData: UpdateProductRequest, prodId: number) {
    return this.http.put(`${this.apiUrl}/${prodId}`, prodData);
  }

  uploadImages(prodId: number, files: File[]) {
    const formData = new FormData();

    files.forEach(file => {
      formData.append("images", file);
    })

    return this.http.post(`${this.apiUrl}/${prodId}/images`, formData);
  }
  
}
