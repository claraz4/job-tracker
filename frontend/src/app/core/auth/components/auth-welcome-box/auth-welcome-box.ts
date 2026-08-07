import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-auth-welcome-box',
  imports: [RouterLink],
  templateUrl: './auth-welcome-box.html',
  styleUrl: './auth-welcome-box.scss',
})
export class AuthWelcomeBox {
  isRegister = input<boolean>(false);
}
