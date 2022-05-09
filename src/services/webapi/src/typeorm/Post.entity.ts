import { Column, Entity, JoinColumn, ManyToOne, OneToOne } from 'typeorm';
import { Address } from './Address.entity';
import { EntityBase } from './EntityBase.entity';
import { User } from './User.entity';

@Entity({ name: 'posts' })
export class Post extends EntityBase {
  @Column({ name: 'title', nullable: false, default: '' })
  title: string;
  @Column({ name: 'content', nullable: false, default: '' })
  content: string;
  @ManyToOne(() => User)
  @JoinColumn()
  user: User;
  @Column({ name: 'userId', nullable: false })
  userId: number;
  @OneToOne(() => Address)
  @JoinColumn()
  address: Address;
}
