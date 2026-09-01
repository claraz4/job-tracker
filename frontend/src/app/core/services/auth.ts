import { inject, Service, signal } from '@angular/core';
import { LoginResponseDto } from '../auth/models/login-response.dto';
import { HttpClient } from '@angular/common/http';
import { catchError, of, tap, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Service()
export class Auth {
  private http = inject(HttpClient);
  private router = inject(Router);

  private accessToken = signal<string | null>(null);

  getAccessToken() {
    return this.accessToken();
  }

  setAccessToken(token: string | null) {
    this.accessToken.set(token);
  }

  clearAccessToken() {
    this.accessToken.set(null);
  }

  isAuthenticated() {
    return !!this.accessToken();
  }

  refreshToken() {
    return this.http
      .post<LoginResponseDto>(
        'auth/refresh',
        {},
        {
          withCredentials: true,
        },
      )
      .pipe(
        tap((response) => this.setAccessToken(response.accessToken)),
        catchError(() => {
          this.setAccessToken(null);
          return of(null);
        }),
      );
  }
}
