import { inject, Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequestDto } from '../models/login-request.dto';
import { LoginResponseDto } from '../models/login-response.dto';
import { RegisterRequestDto } from '../models/register-request.dto';

@Service()
export class AuthApi {
  private http = inject(HttpClient);
  private readonly baseUrl = 'auth';

  login(request: LoginRequestDto) {
    return this.http.post<LoginResponseDto>(`${this.baseUrl}/login`, request, {
      withCredentials: true,
    });
  }

  register(request: RegisterRequestDto) {
    return this.http.post(`${this.baseUrl}/register`, request, {
      withCredentials: true,
    });
  }
}
