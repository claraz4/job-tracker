import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface StatCard {
  title: string;
  value: string;
  subtitle: string;
  icon: string;
  trend?: string;
}

@Component({
  selector: 'app-dashboard-overview',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-overview-component.html',
  styleUrl: './dashboard-overview-component.scss',
})
export class DashboardOverviewComponent {
  cards: StatCard[] = [
    {
      title: 'Total Active',
      value: '24',
      subtitle: '12% from last month',
      icon: 'layers',
      trend: 'up',
    },
    {
      title: 'Applied',
      value: '18',
      subtitle: 'Awaiting initial response',
      icon: 'send',
    },
    {
      title: 'Interviews',
      value: '04',
      subtitle: 'Next scheduled: Tomorrow',
      icon: 'message',
    },
    {
      title: 'Offers',
      value: '02',
      subtitle: 'Decision pending for 1',
      icon: 'badge',
    },
  ];

  exportReport(): void {
    console.log('Export report clicked');
  }
}
