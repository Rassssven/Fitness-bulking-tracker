import { Component, inject } from '@angular/core';
import { NotificationService } from '../../../shared/notification-service';

@Component({
  selector: 'app-catalogue',
  imports: [],
  templateUrl: './catalogue.html',
  styleUrl: './catalogue.css',
})
export class Catalogue {

  private notifService = inject(NotificationService)

}


