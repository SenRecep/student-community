import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { TypeOrmModule } from '@nestjs/typeorm';
import { config } from 'src/config';
import { User } from 'src/typeorm';
import { UsersService } from '../users/services/users/users.service';
import { AuthController } from './controllers/auth/auth.controller';
import { AuthService } from './services/auth/auth.service';
import { JwtStrategy } from './strategies/Jwt.strategy';
import { LocalStrategy } from './strategies/Local.strategy';
import { SessionSerializer } from './utils/SessionSerializer';

@Module({
  imports: [
    TypeOrmModule.forFeature([User]),
    JwtModule.register({
      secret: config().JWT.SECRET,
      signOptions: { expiresIn: config().JWT.EXPIRES_IN },
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
