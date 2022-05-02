import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class User {
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'id' })
  id: number;
  @Column({ name: 'name', nullable: false, default: '' })
  name: string;
  @Column({ name: 'email', nullable: false, default: '' })
  email: string;
  @Column({ name: 'password', nullable: false, default: '' })
  password: string;
}
