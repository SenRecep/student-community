import {
  ValidatorConstraint,
  ValidatorConstraintInterface,
} from 'class-validator';

@ValidatorConstraint({ name: 'isAcademicEmail', async: true })
export class IsAcademicEmail implements ValidatorConstraintInterface {
  validate(email: string): boolean | Promise<boolean> {
    const splitedEmail = email.split('@');
    if (splitedEmail.length !== 2) return false;
    const domain = splitedEmail[1];
    if (domain.includes('.edu.') || domain.includes('.edu')) return true;
    return false;
  }
  defaultMessage?(): string {
    return 'Academic Email is not valid';
  }
}
