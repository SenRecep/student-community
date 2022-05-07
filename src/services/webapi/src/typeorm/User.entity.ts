import { Column, Entity, Unique } from 'typeorm';
import { EntityBase } from './EntityBase.entity';

@Entity({ name: 'users' })
@Unique(['email'])
export class User extends EntityBase {
  @Column({ name: 'first_name', nullable: false, default: '' })
  firstName: string;
  @Column({ name: 'last_name', nullable: false, default: '' })
  lastName: string;
  @Column({ name: 'email', nullable: false, default: '', unique: true })
  email: string;
  @Column({ name: 'password', nullable: false, default: '' })
  password: string;
}
