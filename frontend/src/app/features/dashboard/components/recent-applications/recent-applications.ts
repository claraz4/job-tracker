import { Component } from '@angular/core';
import { NgClass } from '@angular/common';

type ApplicationStatus =
  | 'applied'
  | 'screening'
  | 'interviewing'
  | 'offer-received'
  | 'rejected'
  | 'withdrawn';

interface RecentApplication {
  company: string;
  role: string;
  initials: string;
  dateApplied: string;
  status: ApplicationStatus;
  statusLabel: string;
  engagementIcons: string[];
}

@Component({
  selector: 'app-recent-applications',
  standalone: true,
  imports: [NgClass],
  templateUrl: './recent-applications.html',
  styleUrl: './recent-applications.scss',
})
export class RecentApplicationsComponent {
  applications: RecentApplication[] = [
    {
      company: 'Stripe',
      role: 'Full-stack Engineer',
      initials: 'ST',
      dateApplied: 'Oct 28, 2024',
      status: 'interviewing',
      statusLabel: 'Interviewing',
      engagementIcons: ['mail', 'phone'],
    },
    {
      company: 'Microsoft',
      role: 'Azure Cloud Consultant',
      initials: 'MS',
      dateApplied: 'Nov 02, 2024',
      status: 'applied',
      statusLabel: 'Applied',
      engagementIcons: ['calendar_month'],
    },
    {
      company: 'Revolut',
      role: 'Product Manager',
      initials: 'RE',
      dateApplied: 'Nov 12, 2024',
      status: 'offer-received',
      statusLabel: 'Offer Received',
      engagementIcons: ['description', 'check_circle'],
    },
  ];

  loadMore(): void {
    console.log('Load more applications');
  }
}
