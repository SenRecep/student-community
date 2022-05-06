import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import entities from './typeorm';
import { PassportModule } from '@nestjs/passport';
import { UsersModule } from './modules/users/users.module';
import { AuthModule } from './modules/auth/auth.module';
import { JwtAuthGuard } from './modules/auth/guards/JwtAuth.guard';
import { APP_GUARD } from '@nestjs/core';
import { ConfigModule } from '@nestjs/config';
import { config } from './config';
import { PostsModule } from './modules/posts/posts.module';

@Module({
  imports: [
    UsersModule,
    ConfigModule.forRoot({
      isGlobal: true,
      load: [config],
    }),
    // TypeOrmModule.forRoot({
    //   type: 'mysql',
    //   host: 'localhost',
    //   port: 3306,
    //   username: 'root',
    //   password: '1943332733Rec',
    //   database: 'student_community_db',
    //   entities,
    //   synchronize: true,
    // }),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: config().DB.HOST,
      port: config().DB.PORT,
      username: config().DB.USER,
      password: config().DB.PASSWORD,
      database: config().DB.DATABASE,
      entities,
      synchronize: true,
    }),
    AuthModule,
    PassportModule.register({ session: true }),
    PostsModule,
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
