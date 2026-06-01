import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Product } from '../../models/product';

@Injectable({
  providedIn: 'root',
})
export class ShopService {

  private http = inject(HttpClient);
  private apiUrl = 'https://localhost:8080/products';

  getProducts( 
    search?: string,
    category?: string,
    sort?: string
  ){
    return this.http.get<Product[]>(this.apiUrl, {
        params: {
            search: search ?? '',
            category: category ?? '',
            sort: sort ?? ''
        }
    });

  }
  
}
