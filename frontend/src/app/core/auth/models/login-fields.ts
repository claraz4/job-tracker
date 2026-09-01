import {AuthFormFieldElement} from '../components/auth-form-field/auth-form-field';

export const loginFields: AuthFormFieldElement[] = [
  {
    id: 'username',
    icon: 'alternate_email',
    placeholder: 'Enter your username',
    inputType: 'text',
    required: true,
  },
  {
    id: 'password',
    icon: 'lock',
    placeholder: 'Enter your password',
    inputType: 'password',
    required: true,
  },
];
