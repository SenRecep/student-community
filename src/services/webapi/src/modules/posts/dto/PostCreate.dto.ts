import { Type } from 'class-transformer';
import {
  IsNotEmpty,
  IsNotEmptyObject,
  IsNumber,
  IsString,
  MinLength,
  ValidateNested,
} from 'class-validator';
import { AddressCreateDto } from './AddressCreate.dto';

export class PostCreateDto {
  @IsNotEmpty()
  @IsNumber()
  userId: number;
  @IsNotEmpty()
  @IsString()
  @MinLength(3)
  title: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(5)
  content: string;
  @IsNotEmptyObject()
  @ValidateNested()
  @Type(() => AddressCreateDto)
  address: AddressCreateDto;
}
