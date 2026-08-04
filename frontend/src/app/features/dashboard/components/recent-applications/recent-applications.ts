import { Component, computed, inject } from '@angular/core';
import { DatePipe, NgClass } from '@angular/common';
import { ApplicationApi } from '../../../applications/services/application-api';
import { toSignal } from '@angular/core/rxjs-interop';
import { StatusType } from '../../../applications/types/StatusType';

interface RecentApplication {
  company: string;
  role: string;
  latestActivity: Date;
  status: StatusType;
  engagementIcons: string[];
}

@Component({
  selector: 'app-recent-applications',
  standalone: true,
  imports: [NgClass, DatePipe],
  templateUrl: './recent-applications.html',
  styleUrl: './recent-applications.scss',
})
export class RecentApplicationsComponent {
  private applicationApi = inject(ApplicationApi);
  recentApplications = toSignal(this.applicationApi.getApplications('RECENT_ACTIVITY'), {
    initialValue: [],
  });

  applications = computed<RecentApplication[]>(() =>
    this.recentApplications().map((application) => ({
      company: application.company,
      role: application.position,
      latestActivity: application.lastActivityAt,
      status: application.currentStatus,
      engagementIcons: ['mail', 'phone'],
    })),
  );

  loadMore(): void {
    console.log('Load more applications');
  }
}
