import { inject, Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import DeadlineResponseDto from '../models/deadline-response.dto';

@Service()
export class DeadlineApi {
  private http = inject(HttpClient);
  private readonly baseUrl = 'deadlines';

  getDeadlines() {
    return this.http.get<DeadlineResponseDto[]>(this.baseUrl);
  }
}
