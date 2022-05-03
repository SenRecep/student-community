import { Exclude } from 'class-transformer';

export class UserListDto {
  id: number;
  userName: string;
  firstName: string;
  lastName: string;
  email: string;
  @Exclude()
  password: string;
  constructor(partial: Partial<UserListDto>) {
    Object.assign(this, partial);
  }
}
