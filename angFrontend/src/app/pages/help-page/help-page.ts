import { Component, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-help-page',
  imports: [ReactiveFormsModule],
  templateUrl: './help-page.html',
  styleUrl: './help-page.css',
})
export class HelpPage {

  private fb = inject(FormBuilder);

  isFormOpen = false;

  // helpForm = new FormGroup({
  //   name: new FormControl(''),
  //   email: new FormControl(''),
  //   category: new FormControl(''),
  // });

  helpForm = this.fb.group({
    name: ['', [
      Validators.required,
      Validators.minLength(3)
    ]],
    email: [''],
    issues: this.fb.array([this.createIssue])
  });

  createIssue(): FormGroup {

    return this.fb.group({
      category: ['Bug'],
      description: ['']
    });

  }

  get issues(): FormArray {
    return this.helpForm.get('issues') as FormArray;
  }

  addIssue() {
    this.issues.push(
      this.createIssue()
    );
  }

  removeIssue(index: number) {
    this.issues.removeAt(index);
  }

  submitForm() {
    return;
  }

}
