import { Component } from '@angular/core';

interface StatusStep {
  label: string;
  value: number;
  type: 'applied' | 'screen' | 'interview' | 'offer' | 'rejected';
}

@Component({
  selector: 'app-status-summary',
  standalone: true,
  imports: [],
  templateUrl: './status-summary.html',
  styleUrl: './status-summary.scss',
})
export class StatusSummaryComponent {
  statuses: StatusStep[] = [
    {
      label: 'APP',
      value: 18,
      type: 'applied',
    },
    {
      label: 'SCREEN',
      value: 10,
      type: 'screen',
    },
    {
      label: 'INT',
      value: 4,
      type: 'interview',
    },
    {
      label: 'OFFER',
      value: 2,
      type: 'offer',
    },
    {
      label: 'REJ',
      value: 3,
      type: 'rejected',
    },
  ];

  successRate = '18.4%';
  averageTime = '22d';
}
