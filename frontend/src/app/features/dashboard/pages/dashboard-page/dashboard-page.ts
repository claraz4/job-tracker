import { Component } from '@angular/core';
import { DashboardOverviewComponent } from '../../components/dashboard-overview-component/dashboard-overview-component';
import { UpcomingDeadlinesComponent } from '../../components/upcoming-deadlines/upcoming-deadlines';
import { StatusSummaryComponent } from '../../components/status-summary/status-summary';
import { RecentApplicationsComponent } from '../../components/recent-applications/recent-applications';

@Component({
  selector: 'app-dashboard-page',
  imports: [
    DashboardOverviewComponent,
    UpcomingDeadlinesComponent,
    StatusSummaryComponent,
    RecentApplicationsComponent,
  ],
  templateUrl: './dashboard-page.html',
  styleUrl: './dashboard-page.scss',
})
export class DashboardPage {}
