import { Component, computed, inject, input, signal } from '@angular/core';
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
import { loginFields } from '../../models/login-fields';
import { registerFields } from '../../models/register-fields';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiProblemDetail } from '../../models/ApiProblemDetail';
import { Auth } from '../../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth-form',
  imports: [AuthFormField, ReactiveFormsModule],
  templateUrl: './auth-form.html',
  styleUrl: './auth-form.scss',
})
export class AuthForm {
  authApi = inject(AuthApi);
  authService = inject(Auth);
  router = inject(Router);

  isRegister = input<boolean>(false);

  serverError = signal<string | null>(null);
  fields = computed<AuthFormFieldElement[]>(() =>
    this.isRegister() ? registerFields : loginFields,
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
        error: (error: HttpErrorResponse) => {
          const errorDetails = error.error as ApiProblemDetail;
          this.serverError.set(errorDetails.detail);
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
          this.authService.setAccessToken(response.accessToken);
          this.router.navigate(['/']);
        },
        error: (error: HttpErrorResponse) => {
          const errorDetails = error.error as ApiProblemDetail;
          this.serverError.set(errorDetails.detail);
        },
      });
    }
  }
}
