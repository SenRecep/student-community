import { Type } from 'class-transformer';
import { IsString, MinLength, ValidateNested } from 'class-validator';
import { AddressUpdateDto } from './AddressUpdate.dto';

export class PostUpdateDto {
  @IsString()
  @MinLength(3)
  title?: string;
  @IsString()
  @MinLength(5)
  content?: string;
  @ValidateNested()
  @Type(() => AddressUpdateDto)
  address?: AddressUpdateDto;
}
