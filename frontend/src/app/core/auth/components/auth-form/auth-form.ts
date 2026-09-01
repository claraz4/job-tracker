import { Component, computed, inject, input } from '@angular/core';
import { AuthFormField, AuthFormFieldElement } from '../auth-form-field/auth-form-field';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuthApi } from '../../services/auth-api';
import { LoginRequestDto } from '../../models/login-request.dto';
import { RegisterRequestDto } from '../../models/register-request.dto';

@Component({
  selector: 'app-auth-form',
  imports: [AuthFormField, ReactiveFormsModule],
  templateUrl: './auth-form.html',
  styleUrl: './auth-form.scss',
})
export class AuthForm {
  authApi = inject(AuthApi);
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
      id: 'name',
      icon: 'person',
      placeholder: 'Enter your name',
      inputType: 'text',
    },
    {
      id: 'position',
      icon: 'badge',
      placeholder: 'Enter your position',
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

  authForm = computed(() => {
    const controls: Record<string, FormControl<string>> = {};

    for (const field of this.fields()) {
      controls[field.id] = new FormControl('', {
        nonNullable: true,
      });
    }

    return new FormGroup(controls);
  });

  onSubmit() {
    if (this.isRegister()) {
      const formValue = this.authForm().value;
      const registerRequest: RegisterRequestDto = {
        username: formValue['username'] || '',
        password: formValue['password'] || '',
        name: formValue['name'] || '',
        position: formValue['position'] || '',
      };

      this.authApi.register(registerRequest).subscribe({
        next: (response) => {
          console.log('Registered:', response);
        },
        error: (error) => {
          console.error('Register failed:', error);
        },
      });
    } else {
      const formValue = this.authForm().value;
      const loginRequest: LoginRequestDto = {
        username: formValue['username'] || '',
        password: formValue['password'] || '',
      };

      this.authApi.login(loginRequest).subscribe({
        next: (response) => {
          console.log('Logged in:', response);
        },
        error: (error) => {
          console.error('Login failed:', error);
        },
      });
    }
  }
}
