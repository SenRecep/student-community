import {
  Body,
  Controller,
  Get,
  Inject,
  NotFoundException,
  Param,
  ParseIntPipe,
  Post,
  UseFilters,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { HttpExceptionFilter } from 'src/filters/HttpException.filter';
import { PostCreateDto } from '../../dto/PostCreate.dto';
import { PostsService } from '../../services/posts/posts.service';

@Controller('posts')
export class PostsController {
  constructor(
    @Inject(PostsService) private readonly postsService: PostsService,
  ) {}

  @Get()
  getAll() {
    return this.postsService.getAllAsync();
  }

  @Get(':id')
  @UseFilters(HttpExceptionFilter)
  async getById(@Param('id', ParseIntPipe) id: number) {
    const found = await this.postsService.getByIdAsync(id);
    if (!found) throw new NotFoundException('Post not found');
    return found;
  }

  @Post()
  @UsePipes(ValidationPipe)
  createUser(@Body() postCreateDto: PostCreateDto) {
    return this.postsService.createAsync(postCreateDto);
  }
}
