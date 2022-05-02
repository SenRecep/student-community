import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from 'src/typeorm';
import { UsersController } from './controllers/users/users.controller';
import { UsersService } from './services/users/users.service';

@Module({
  imports: [TypeOrmModule.forFeature([User])],
  controllers: [UsersController],
  providers: [
    {
      provide: 'UsersService',
      useClass: UsersService,
    },
  ],
})
export class UsersModule {}
