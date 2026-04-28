import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProductCard } from '../../products/product-card/product-card';

@Component({
  selector: 'app-shop',
  imports: [CommonModule, RouterLink],
  templateUrl: './shop.html',
  styleUrl: './shop.css',
})
export class Shop {

}
