import {
  Body,
  Controller,
  Delete,
  Get,
  Inject,
  NotFoundException,
  Param,
  ParseIntPipe,
  Post,
  Put,
  Query,
  UseFilters,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { HttpExceptionFilter } from 'src/filters/HttpException.filter';
import { PostCreateDto } from '../../dto/PostCreate.dto';
import { PostUpdateDto } from '../../dto/PostUpdate.dto';
import { PostsService } from '../../services/posts/posts.service';

@Controller('posts')
export class PostsController {
  constructor(
    @Inject(PostsService) private readonly postsService: PostsService,
  ) {}

  @Get()
  getAll() {
    return this.postsService.getAllWhitoutDeleted();
  }
  @Get('pager')
  async getAllPager(@Query() { take, skip }) {
    const data = await this.postsService.getAllPager(take, skip);
    console.log('take: ', take);
    console.log('skip: ', skip);
    console.log('data count: ', data.length);
    return data;
  }

  @Get(':id')
  @UseFilters(HttpExceptionFilter)
  async getById(@Param('id', ParseIntPipe) id: number) {
    const found = await this.postsService.getByIdAsync(id);
    if (!found) throw new NotFoundException('Post not found');
    return found;
  }

  @Delete(':id')
  delete(@Param('id', ParseIntPipe) id: number) {
    return this.postsService.deleteAsync(id);
  }
  @Put(':id')
  update(
    @Param('id', ParseIntPipe) id: number,
    @Body() postUpdateDto: PostUpdateDto,
  ) {
    return this.postsService.updateAsync(id, postUpdateDto);
  }

  @Post()
  @UsePipes(ValidationPipe)
  createUser(@Body() postCreateDto: PostCreateDto) {
    return this.postsService.createAsync(postCreateDto);
  }
}
