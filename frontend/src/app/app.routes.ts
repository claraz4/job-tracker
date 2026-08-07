import { Routes } from '@angular/router';
import { DashboardPage } from './features/dashboard/pages/dashboard-page/dashboard-page';
import { ApplicationsPage } from './features/applications/pages/applications-page/applications-page';
import { LoginPage } from './core/auth/pages/login-page/login-page';
import { RegisterPage } from './core/auth/pages/register-page/register-page';
import { AuthenticatedLayout } from './core/layout/authenticated-layout/authenticated-layout';
import { AuthLayout } from './core/layout/auth-layout/auth-layout';

export const routes: Routes = [
  {
    path: '',
    component: AuthenticatedLayout,
    children: [
      {
        path: '',
        component: DashboardPage,
      },
      {
        path: 'applications',
        component: ApplicationsPage,
      },
    ],
  },
  {
    path: '',
    component: AuthLayout,
    children: [
      {
        path: 'login',
        component: LoginPage,
      },
      {
        path: 'register',
        component: RegisterPage,
      },
    ],
  },
  {
    path: '**',
    redirectTo: 'dashboard',
  },
];
