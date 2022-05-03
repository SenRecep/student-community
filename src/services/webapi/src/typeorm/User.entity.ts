import { Column, Entity, PrimaryGeneratedColumn, Unique } from 'typeorm';

@Entity({ name: 'users' })
@Unique(['email'])
export class User {
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'id' })
  id: number;
  @Column({ name: 'first_name', nullable: false, default: '' })
  firstName: string;
  @Column({ name: 'last_name', nullable: false, default: '' })
  lastName: string;
  @Column({ name: 'email', nullable: false, default: '', unique: true })
  email: string;
  @Column({ name: 'password', nullable: false, default: '' })
  password: string;
}
