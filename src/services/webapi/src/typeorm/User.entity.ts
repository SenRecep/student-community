import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'users' })
export class User {
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'id' })
  id: number;
  @Column({ name: 'user_name', nullable: false, default: '' })
  userName: string;
  @Column({ name: 'first_name', nullable: false, default: '' })
  firstName: string;
  @Column({ name: 'last_name', nullable: false, default: '' })
  lastName: string;
  @Column({ name: 'email', nullable: false, default: '' })
  email: string;
  @Column({ name: 'password', nullable: false, default: '' })
  password: string;
}
