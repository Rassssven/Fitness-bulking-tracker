import { Component, inject } from '@angular/core';
import { CartService } from '../../../services/CartService';
import { NotificationService } from '../../../shared/notification-service';

@Component({
  selector: 'app-cart',
  imports: [],
  templateUrl: './cart.html',
  styleUrl: './cart.css',
})
export class Cart {

  cartServ = inject(CartService);
  notifServ = inject(NotificationService);



}
