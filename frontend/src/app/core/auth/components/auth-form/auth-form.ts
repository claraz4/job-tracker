import { Component, computed, inject, input } from '@angular/core';
import { AuthFormField, AuthFormFieldElement } from '../auth-form-field/auth-form-field';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { AuthApi } from '../../services/auth-api';
import { LoginRequestDto } from '../../models/login-request.dto';
import { RegisterRequestDto } from '../../models/register-request.dto';
import { strongPasswordValidator } from '../../validators/strongPasswordValidator';
import { correctUsernameValidator } from '../../validators/correctUsernameValidator';
import { passwordsMatchValidator } from '../../validators/passwordsMatchValidator';

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
      required: true,
    },
    {
      id: 'password',
      icon: 'lock',
      placeholder: 'Enter your password',
      inputType: 'password',
      required: true,
    },
  ];

  registerFields: AuthFormFieldElement[] = [
    {
      id: 'name',
      icon: 'person',
      placeholder: 'Enter your name',
      inputType: 'text',
      required: true,
    },
    {
      id: 'position',
      icon: 'badge',
      placeholder: 'Enter your position',
      inputType: 'text',
      required: true,
    },
    {
      id: 'username',
      icon: 'alternate_email',
      placeholder: 'Enter your username',
      inputType: 'text',
      required: true,
      correctUsername: true,
    },
    {
      id: 'password',
      icon: 'lock',
      placeholder: 'Enter your password',
      inputType: 'password',
      required: true,
      strongPassword: true,
      minLength: 8,
    },
    {
      id: 'confirmPassword',
      icon: 'lock',
      placeholder: 'Confirm your password',
      inputType: 'password',
      required: true,
    },
  ];

  fields = computed<AuthFormFieldElement[]>(() =>
    this.isRegister() ? this.registerFields : this.loginFields,
  );

  title = computed<string>(() => (this.isRegister() ? 'Register' : 'Login'));

  authForm = computed(() => {
    const controls: Record<string, FormControl<string>> = {};

    for (const field of this.fields()) {
      const validators: ValidatorFn[] = [];

      if (field.required) {
        validators.push(Validators.required);
      }

      if (field.strongPassword) {
        validators.push(strongPasswordValidator);
      }

      if (field.correctUsername) {
        validators.push(correctUsernameValidator);
      }

      if (field.minLength) {
        validators.push(Validators.minLength(field.minLength));
      }

      controls[field.id] = new FormControl('', {
        nonNullable: true,
        validators,
      });
    }

    return new FormGroup(controls, {
      validators: this.isRegister() ? [passwordsMatchValidator] : [],
    });
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
