import { IsEmail, IsNotEmpty, MinLength } from 'class-validator';

export class CreateUserDto {
  @IsNotEmpty()
  @MinLength(3)
  userName: string;
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
  email: string;
}
