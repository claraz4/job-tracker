import { Service } from '@angular/core';

@Service()
export class Auth {
  private userId: number | null = null;

  setUserId(userId: number): void {
    this.userId = userId;
  }

  getUserId(): number | null {
    return this.userId;
  }
}
