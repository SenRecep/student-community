import { Column, Entity } from 'typeorm';
import { EntityBase } from './EntityBase.entity';

@Entity('addresses')
export class Address extends EntityBase {
  @Column({ name: 'latitude', type: 'float', nullable: false, default: 0.0 })
  latitude: number;
  @Column({ name: 'longitude', type: 'float', nullable: false, default: 0.0 })
  longitude: number;
  @Column({ name: 'address', nullable: false, default: '' })
  address: string;
}
