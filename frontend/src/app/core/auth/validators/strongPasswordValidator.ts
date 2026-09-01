import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const strongPasswordValidator: ValidatorFn = (
  control: AbstractControl,
): ValidationErrors | null => {
  const value = control.value;

  if (!value) {
    return null;
  }

  const errors: ValidationErrors = {};

  if (!/[A-Z]/.test(value)) {
    errors['uppercase'] = true;
  }

  if (!/[a-z]/.test(value)) {
    errors['lowercase'] = true;
  }

  if (!/\d/.test(value)) {
    errors['number'] = true;
  }

  if (!/[^A-Za-z0-9]/.test(value)) {
    errors['symbol'] = true;
  }

  return Object.keys(errors).length ? errors : null;
};
