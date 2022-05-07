import { Column, Entity, JoinColumn, OneToOne } from 'typeorm';
import { Address } from './Address.entity';
import { EntityBase } from './EntityBase.entity';

@Entity({ name: 'posts' })
export class Post extends EntityBase {
  @Column({ name: 'title', nullable: false, default: '' })
  title: string;
  @Column({ name: 'content', nullable: false, default: '' })
  content: string;
  @OneToOne(() => Address)
  @JoinColumn()
  address: Address;
}
