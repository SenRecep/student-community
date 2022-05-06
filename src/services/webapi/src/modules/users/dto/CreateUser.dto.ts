import {
  IsEmail,
  IsNotEmpty,
  IsString,
  MaxLength,
  MinLength,
  Validate,
} from 'class-validator';
import { PasswordValidation } from 'class-validator-password-check';
import { IsAcademicEmail } from 'src/extensions/CustomValidation/IsAcademicEmailValidator';
import { PasswordRequirement } from 'src/utils/passwordRequirement';

export class CreateUserDto {
  @IsNotEmpty()
  @IsString()
  @MinLength(3)
  firstName: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(3)
  lastName: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(8)
  @MaxLength(36)
  @Validate(PasswordValidation, [PasswordRequirement])
  password: string;
  @IsNotEmpty()
  @IsString()
  @IsEmail()
  @Validate(IsAcademicEmail)
  email: string;
}
