import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelloService } from './services/item';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html'
})
export class AppComponent {

  message = signal('');

  constructor(private helloService: HelloService) {
    this.helloService.getHello().subscribe(data => {
      this.message.set(data);
    });
  }
}
