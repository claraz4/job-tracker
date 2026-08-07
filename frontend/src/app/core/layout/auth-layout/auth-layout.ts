import { Component, inject } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { AuthWelcomeBox } from '../../auth/components/auth-welcome-box/auth-welcome-box';
import { filter } from 'rxjs';

@Component({
  selector: 'app-auth-layout',
  imports: [RouterOutlet, AuthWelcomeBox],
  templateUrl: './auth-layout.html',
  styleUrl: './auth-layout.scss',
})
export class AuthLayout {
  private router = inject(Router);

  isRegister = false;

  constructor() {
    this.updateMode();

    this.router.events.pipe(filter((event) => event instanceof NavigationEnd)).subscribe(() => {
      this.updateMode();
    });
  }

  private updateMode() {
    this.isRegister = this.router.url.includes('/register');
  }
}
