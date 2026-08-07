import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthWelcomeBox } from './auth-welcome-box';

describe('AuthWelcomeBox', () => {
  let component: AuthWelcomeBox;
  let fixture: ComponentFixture<AuthWelcomeBox>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthWelcomeBox],
    }).compileComponents();

    fixture = TestBed.createComponent(AuthWelcomeBox);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
