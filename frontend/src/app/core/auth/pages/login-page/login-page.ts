import { Component } from '@angular/core';
import { AuthForm } from '../../components/auth-form/auth-form';

@Component({
  selector: 'app-login-page',
  imports: [AuthForm],
  templateUrl: './login-page.html',
  styleUrl: './login-page.scss',
})
export class LoginPage {}
