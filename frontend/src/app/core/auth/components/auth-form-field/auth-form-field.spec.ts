import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthFormField } from './auth-form-field';

describe('AuthFormField', () => {
  let component: AuthFormField;
  let fixture: ComponentFixture<AuthFormField>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthFormField],
    }).compileComponents();

    fixture = TestBed.createComponent(AuthFormField);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
