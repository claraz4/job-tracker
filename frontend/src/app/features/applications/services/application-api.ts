import { inject, Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import ApplicationStatsResponseDto from '../models/application-stats-response.dto';
import ApplicationResponseDto from '../models/application-response.dto';

type ApplicationSort = 'CREATED' | 'RECENT_ACTIVITY';

@Service()
export class ApplicationApi {
  private http = inject(HttpClient);
  private readonly baseUrl = 'applications';

  getStats() {
    return this.http.get<ApplicationStatsResponseDto>(`${this.baseUrl}/stats`);
  }

  getApplications(sort: ApplicationSort = 'CREATED') {
    return this.http.get<ApplicationResponseDto[]>(this.baseUrl, {
      params: { sort },
    });
  }
}
