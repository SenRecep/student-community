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
  Req,
  UseFilters,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { Request } from 'express';
import { HttpExceptionFilter } from 'src/filters/HttpException.filter';
import { UserListDto } from 'src/modules/users/dto/UserList.dto';
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
    return await this.postsService.getAllPager(take, skip);
  }
  @Get('/checkowner/:postId')
  async checkOwner(
    @Param('postId', ParseIntPipe) postId: number,
    @Req() req: Request,
  ) {
    const found = await this.postsService.getByIdAsync(postId);
    const user = new UserListDto(req.user);
    return found?.userId === user.id;
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
  createUser(@Body() postCreateDto: PostCreateDto, @Req() req: Request) {
    postCreateDto.userId = req.user['id'];
    return this.postsService.createAsync(postCreateDto);
  }
}
