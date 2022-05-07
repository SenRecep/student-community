import { Exclude } from 'class-transformer';
import { EntityBaseDto } from 'src/typeorm/EntityBase.entity';

export class UserListDto extends EntityBaseDto<UserListDto> {
  firstName: string;
  lastName: string;
  email: string;
  @Exclude()
  password: string;
}
