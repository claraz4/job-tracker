import { Component } from '@angular/core';

interface DeadlineItem {
  title: string;
  description: string;
  daysLeft: string;
  date: string;
  icon: string;
  progress: number;
  status: 'urgent' | 'normal';
}

@Component({
  selector: 'app-upcoming-deadlines',
  standalone: true,
  imports: [],
  templateUrl: './upcoming-deadlines.html',
  styleUrl: './upcoming-deadlines.scss',
})
export class UpcomingDeadlinesComponent {
  deadlines: DeadlineItem[] = [
    {
      title: 'Software Engineer II • Linear',
      description: 'Technical Assessment Due',
      daysLeft: '2 days left',
      date: 'NOV 24, 2024',
      icon: 'code',
      progress: 78,
      status: 'urgent',
    },
    {
      title: 'Product Designer • Airbnb',
      description: 'Portfolio Submission',
      daysLeft: '5 days left',
      date: 'NOV 27, 2024',
      icon: 'palette',
      progress: 40,
      status: 'normal',
    },
    {
      title: 'Data Analyst • Datadog',
      description: 'Follow-up Email',
      daysLeft: '8 days left',
      date: 'NOV 30, 2024',
      icon: 'analytics',
      progress: 15,
      status: 'normal',
    },
  ];
}
