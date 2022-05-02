import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import entities from './typeorm';
import { PassportModule } from '@nestjs/passport';
import { UsersModule } from './modules/users/users.module';
import { AuthModule } from './modules/auth/auth.module';
import { JwtAuthGuard } from './modules/auth/guards/JwtAuth.guard';
import { APP_GUARD } from '@nestjs/core';

@Module({
  imports: [
    UsersModule,
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: '1943332733Rec',
      database: 'student_community_db',
      entities,
      synchronize: true,
    }),
    AuthModule,
    PassportModule.register({ session: true }),
  ],
  controllers: [],
  providers: [
    {
      provide: APP_GUARD,
      useClass: JwtAuthGuard,
    },
  ],
})
export class AppModule {}
