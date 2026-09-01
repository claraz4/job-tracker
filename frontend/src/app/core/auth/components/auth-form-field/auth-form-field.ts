import { Component, input } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

export interface AuthFormFieldElement {
  id: string;
  icon: string;
  placeholder: string;
  inputType: 'text' | 'password';
}

@Component({
  selector: 'app-auth-form-field',
  imports: [ReactiveFormsModule],
  templateUrl: './auth-form-field.html',
  styleUrl: './auth-form-field.scss',
})
export class AuthFormField {
  field = input.required<AuthFormFieldElement>();
  control = input.required<FormControl>();
}
