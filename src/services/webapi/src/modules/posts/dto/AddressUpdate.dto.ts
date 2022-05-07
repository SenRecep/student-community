import { IsNotEmpty, IsNumber, IsString, Max, Min } from 'class-validator';

export class AddressUpdateDto {
  @IsNumber()
  @Min(-90)
  @Max(90)
  latitude?: number;
  @IsNumber()
  @Min(-181)
  @Max(181)
  longitude?: number;
  @IsString()
  @IsNotEmpty()
  address?: string;
}
