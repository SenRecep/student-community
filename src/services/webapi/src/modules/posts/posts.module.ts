import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Address, Post } from 'src/typeorm';
import { PostsController } from './controllers/posts/posts.controller';
import { PostsService } from './services/posts/posts.service';

@Module({
  imports: [TypeOrmModule.forFeature([Post, Address])],
  controllers: [PostsController],
  providers: [PostsService],
})
export class PostsModule {}
