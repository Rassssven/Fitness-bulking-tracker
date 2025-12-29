import { Component } from '@angular/core';
import { TestSpringService } from './service/test-spring';

@Component({
  selector: 'app-root',
  standalone: true,
  template: `
    <h1>Angular + Spring Boot</h1>
    <p>{{ message }}</p>
  `
})
export class App {

  message = '';

  constructor(private testService: TestSpringService) {
    this.testService.getTest().subscribe({
      next: res => {
        console.log('Răspuns din backend:', res);
        this.message = res;
      },
      error: err => {
        console.error('Eroare:', err);
      }
    });
  }
}

