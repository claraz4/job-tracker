import { Component, input } from '@angular/core';

export interface AuthFormFieldElement {
  id: string;
  icon: string;
  placeholder: string;
  inputType: 'text' | 'password';
}

@Component({
  selector: 'app-auth-form-field',
  imports: [],
  templateUrl: './auth-form-field.html',
  styleUrl: './auth-form-field.scss',
})
export class AuthFormField {
  field = input.required<AuthFormFieldElement>();
}
