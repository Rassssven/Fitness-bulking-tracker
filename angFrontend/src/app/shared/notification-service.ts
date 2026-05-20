import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
    message = new BehaviorSubject<string>('');
    visible = new BehaviorSubject<boolean>(false);
    type = new BehaviorSubject<'success' | 'error'>('success');
  
    showSuccess(message: string) {
  
      this.message.next(message);
      this.type.next('success');
      this.visible.next(true);
  
      this.hideAfterDelay();
    }
  
    showError(message: string) {
  
      this.message.next(message);
      this.type.next('error');
      this.visible.next(true);
  
      this.hideAfterDelay();
    }
  
    private hideAfterDelay() {
  
      setTimeout(() => {
        this.visible.next(false);
      }, 3000);
    }
}
