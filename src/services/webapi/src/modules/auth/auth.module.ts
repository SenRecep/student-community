import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from 'src/typeorm';
import { UsersService } from '../users/services/users/users.service';
import { JwtConstants } from './constants';
import { AuthController } from './controllers/auth/auth.controller';
import { AuthService } from './services/auth/auth.service';
import { JwtStrategy } from './strategies/Jwt.strategy';
import { LocalStrategy } from './strategies/Local.strategy';
import { SessionSerializer } from './utils/SessionSerializer';

@Module({
  imports: [
    TypeOrmModule.forFeature([User]),
    JwtModule.register({
      secret: JwtConstants.secret,
      signOptions: { expiresIn: '60s' },
    }),
  ],
  controllers: [AuthController],
  providers: [
    {
      provide: 'AuthService',
      useClass: AuthService,
    },
    {
      provide: 'UsersService',
      useClass: UsersService,
    },
    LocalStrategy,
    SessionSerializer,
    JwtStrategy,
  ],
})
export class AuthModule {}
