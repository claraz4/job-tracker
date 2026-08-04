import { Service } from '@angular/core';

@Service()
export class Auth {
  private userId: number = 1;

  setUserId(userId: number): void {
    this.userId = userId;
  }

  getUserId(): number {
    return this.userId;
  }
}
