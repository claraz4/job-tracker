import { Component, inject, computed } from '@angular/core';
import { DeadlineApi } from '../../../deadlines/services/deadline-api';
import { toSignal } from '@angular/core/rxjs-interop';
import { DatePipe, UpperCasePipe } from '@angular/common';

interface DeadlineItem {
  id: number;
  title: string;
  description: string;
  daysLeft: string;
  date: Date;
  icon: string;
  status: 'urgent' | 'normal';
}

@Component({
  selector: 'app-upcoming-deadlines',
  standalone: true,
  imports: [DatePipe, UpperCasePipe],
  templateUrl: './upcoming-deadlines.html',
  styleUrl: './upcoming-deadlines.scss',
})
export class UpcomingDeadlinesComponent {
  private deadlineApi = inject(DeadlineApi);
  deadlines = toSignal(this.deadlineApi.getDeadlines(), { initialValue: [] });

  // TODO: EVENTUALLY MAKE THE LIMIT IN THE BACKEND
  computedDeadlines = computed<DeadlineItem[]>(() =>
    this.deadlines()
      .slice(0, 3)
      .map((deadline) => {
        const dueDate = new Date(deadline.dueAt);
        const diff = dueDate.getTime() - Date.now();

        const daysLeft = Math.ceil(diff / (1000 * 60 * 60 * 24));

        return {
          id: deadline.id,
          title: `${deadline.position} • ${deadline.company}`,
          description: deadline.title,
          daysLeft:
            daysLeft >= 0 ? `${daysLeft} day${daysLeft == 1 ? '' : 's'} left` : 'Deadline passed',
          date: dueDate,
          icon: 'code',
          status: daysLeft < 3 ? 'urgent' : 'normal',
        };
      }),
  );
}
