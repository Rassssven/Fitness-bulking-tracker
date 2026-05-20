import { Component, inject } from '@angular/core';
import { NotificationService } from '../notification-service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-notification',
  imports: [AsyncPipe],
  templateUrl: './notification.html',
  styleUrl: './notification.css',
})
export class Notification {

  notificationService = inject(NotificationService);

}
