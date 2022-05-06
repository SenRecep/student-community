import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity('addresses')
export class Address {
  @PrimaryGeneratedColumn({ type: 'bigint', name: 'id' })
  id: number;
  @Column({ name: 'latitude', type: 'float', nullable: false, default: 0.0 })
  latitude: number;
  @Column({ name: 'longitude', type: 'float', nullable: false, default: 0.0 })
  longitude: number;
  @Column({ name: 'address', nullable: false, default: '' })
  address: string;
}
