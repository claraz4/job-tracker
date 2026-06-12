import { Routes } from '@angular/router';
import { DashboardPage } from './features/dashboard/pages/dashboard-page/dashboard-page';
import { ApplicationsPage } from './features/applications/pages/applications-page/applications-page';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    component: DashboardPage,
  },
  {
    path: 'applications',
    component: ApplicationsPage,
  },
];
