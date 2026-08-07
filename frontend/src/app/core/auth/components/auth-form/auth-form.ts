import { Component, computed, input } from '@angular/core';
import { AuthFormField, AuthFormFieldElement } from '../auth-form-field/auth-form-field';

@Component({
  selector: 'app-auth-form',
  imports: [AuthFormField],
  templateUrl: './auth-form.html',
  styleUrl: './auth-form.scss',
})
export class AuthForm {
  isRegister = input<boolean>(false);

  loginFields: AuthFormFieldElement[] = [
    {
      id: 'username',
      icon: 'alternate_email',
      placeholder: 'Enter your username',
      inputType: 'text',
    },
    {
      id: 'password',
      icon: 'lock',
      placeholder: 'Enter your password',
      inputType: 'password',
    },
  ];

  registerFields: AuthFormFieldElement[] = [
    {
      id: 'Name',
      icon: 'person',
      placeholder: 'Enter your name',
      inputType: 'text',
    },
    ...this.loginFields,
    {
      id: 'confirmPassword',
      icon: 'lock',
      placeholder: 'Confirm your password',
      inputType: 'password',
    },
  ];

  fields = computed<AuthFormFieldElement[]>(() =>
    this.isRegister() ? this.registerFields : this.loginFields,
  );

  title = computed<string>(() => (this.isRegister() ? 'Register' : 'Login'));
}
