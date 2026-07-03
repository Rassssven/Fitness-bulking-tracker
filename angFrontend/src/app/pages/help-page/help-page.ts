import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-help-page',
  imports: [ReactiveFormsModule],
  templateUrl: './help-page.html',
  styleUrl: './help-page.css',
})
export class HelpPage {

  isFormOpen = false;

  helpForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    category: new FormControl(''),
    description: new FormControl('')
  });

  submitForm() {
    return;
  }

}
