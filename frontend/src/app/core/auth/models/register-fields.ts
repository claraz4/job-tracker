import { AuthFormFieldElement } from '../components/auth-form-field/auth-form-field';

export const registerFields: AuthFormFieldElement[] = [
  {
    id: 'name',
    icon: 'person',
    placeholder: 'Enter your name',
    inputType: 'text',
    required: true,
  },
  {
    id: 'position',
    icon: 'badge',
    placeholder: 'Enter your position',
    inputType: 'text',
    required: true,
  },
  {
    id: 'username',
    icon: 'alternate_email',
    placeholder: 'Enter your username',
    inputType: 'text',
    required: true,
    correctUsername: true,
  },
  {
    id: 'password',
    icon: 'lock',
    placeholder: 'Enter your password',
    inputType: 'password',
    required: true,
    strongPassword: true,
    minLength: 8,
  },
  {
    id: 'confirmPassword',
    icon: 'lock',
    placeholder: 'Confirm your password',
    inputType: 'password',
    required: true,
  },
];
