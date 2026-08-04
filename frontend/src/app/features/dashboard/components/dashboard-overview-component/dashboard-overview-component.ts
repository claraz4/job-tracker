import { Component, computed, inject } from '@angular/core';
import { ApplicationApi } from '../../../applications/services/application-api';
import { toSignal } from '@angular/core/rxjs-interop';

interface StatCard {
  title: string;
  value: number;
  subtitle?: string;
  icon: string;
}

@Component({
  selector: 'app-dashboard-overview',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-overview-component.html',
  styleUrl: './dashboard-overview-component.scss',
})
export class DashboardOverviewComponent {
  private applicationApi = inject(ApplicationApi);
  stats = toSignal(this.applicationApi.getStats(), { initialValue: null });

  cards = computed<StatCard[]>(() => {
    return [
      {
        title: 'Total Active',
        value: this.stats()?.active ?? 0,
        subtitle: 'Expecting next steps',
        icon: 'layers',
      },
      {
        title: 'Applied',
        value: this.stats()?.applied ?? 0,
        subtitle: 'Awaiting initial response',
        icon: 'send',
      },
      {
        title: 'Interviews',
        value: this.stats()?.interviews ?? 0,
        subtitle: 'To prepare for',
        icon: 'message',
      },
      {
        title: 'Offers',
        value: this.stats()?.offers ?? 0,
        subtitle: 'Decision pending',
        icon: 'badge',
      },
    ];
  });

  exportReport(): void {
    console.log('Export report clicked');
  }
}
