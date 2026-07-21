import { Component, inject } from '@angular/core';
import { NotificationService } from '../../../shared/notification-service';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-catalogue',
  imports: [RouterLink],
  templateUrl: './catalogue.html',
  styleUrl: './catalogue.css',
})
export class Catalogue {

  private route = inject(ActivatedRoute)
  private notifService = inject(NotificationService)

}


