import {
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  UpdateDateColumn,
} from 'typeorm';

export abstract class EntityBase {
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'id' })
  id: number;
  @Column({ nullable: false, default: false })
  isDeleted: boolean;
  @CreateDateColumn()
  createdTime: Date;
  @UpdateDateColumn()
  updatedTime: Date;
}

export abstract class EntityBaseDto<T> {
  id: number;
  isDeleted: boolean;
  createdTime: Date;
  updatedTime: Date;
  constructor(partial: Partial<T>) {
    Object.assign(this, partial);
  }
}
