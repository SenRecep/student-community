import {
  Column,
  Entity,
  JoinColumn,
  OneToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { Address } from './Address.entity';

@Entity({ name: 'posts' })
export class Post {
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'id' })
  id: number;
  @Column({ name: 'title', nullable: false, default: '' })
  title: string;
  @Column({ name: 'content', nullable: false, default: '' })
  content: string;
  @OneToOne(() => Address)
  @JoinColumn()
  address: Address;
}
