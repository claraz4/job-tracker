import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const correctUsernameValidator: ValidatorFn = (
  control: AbstractControl,
): ValidationErrors | null => {
  const value = control.value as string;

  if (!value) {
    return null;
  }

  const usernamePattern = /^[A-Za-z][A-Za-z0-9._]*$/;

  return usernamePattern.test(value) ? null : { invalidUsername: true };
};
