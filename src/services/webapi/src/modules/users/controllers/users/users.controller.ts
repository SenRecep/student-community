import {
  Body,
  ClassSerializerInterceptor,
  Controller,
  Get,
  Inject,
  NotFoundException,
  Param,
  ParseIntPipe,
  Post,
  UseFilters,
  UseInterceptors,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { Public } from 'src/modules/auth/guards/Public.guard';
import { CreateUserDto } from '../../dto/CreateUser.dto';
import { HttpExceptionFilter } from '../../filters/HttpException.filter';
import { UsersService } from '../../services/users/users.service';

@Controller('users')
export class UsersController {
  constructor(
    @Inject('UsersService') private readonly userService: UsersService,
  ) {}

  @UseInterceptors(ClassSerializerInterceptor)
  @Get('')
  getUsers() {
    return this.userService.getUsers();
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseFilters(HttpExceptionFilter)
  @Get('id/:id')
  getUserById(@Param('id', ParseIntPipe) id: number) {
    const user = this.userService.findById(id);
    if (!user) throw new NotFoundException();
    return user;
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseFilters(HttpExceptionFilter)
  @Get('username/:username')
  getUserByName(@Param('username') username: string) {
    const user = this.userService.findByUserName(username);
    if (!user) throw new NotFoundException();
    return user;
  }

  @Public()
  @Post('create')
  @UsePipes(ValidationPipe)
  createUser(@Body() createUserDto: CreateUserDto) {
    return this.userService.createUser(createUserDto);
  }
}
