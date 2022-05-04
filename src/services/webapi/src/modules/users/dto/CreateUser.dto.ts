import { IsEmail, IsNotEmpty, MinLength, Validate } from 'class-validator';
import { IsAcademicEmail } from 'src/extensions/CustomValidation/IsAcademicEmailValidator';

export class CreateUserDto {
  @IsNotEmpty()
  @MinLength(3)
  firstName: string;
  @IsNotEmpty()
  @MinLength(3)
  lastName: string;
  @IsNotEmpty()
  @MinLength(8)
  password: string;
  @IsNotEmpty()
  @IsEmail()
  @Validate(IsAcademicEmail)
  email: string;
}
