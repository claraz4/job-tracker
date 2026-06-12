import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

interface SidebarItem {
  label: string;
  icon: string;
  route: string;
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  templateUrl: './sidebar.html',
  styleUrls: ['./sidebar.scss'],
  imports: [RouterLink, RouterLinkActive],
})
export class SidebarComponent {
  navItems: SidebarItem[] = [
    { label: 'Dashboard', icon: 'grid_view', route: '/' },
    { label: 'Applications', icon: 'business_center', route: '/applications' },
    { label: 'Companies', icon: 'apartment', route: '/companies' },
    { label: 'Deadlines', icon: 'event_upcoming', route: '/deadlines' },
    { label: 'Statistics', icon: 'query_stats', route: '/statistics' },
    { label: 'Settings', icon: 'settings', route: '/settings' },
  ];
}
