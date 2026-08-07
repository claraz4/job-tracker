import { Component } from '@angular/core';
import { AuthForm } from '../../components/auth-form/auth-form';

@Component({
  selector: 'app-register-page',
  imports: [AuthForm],
  templateUrl: './register-page.html',
  styleUrl: './register-page.scss',
})
export class RegisterPage {}
