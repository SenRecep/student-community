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
  async getUserById(@Param('id', ParseIntPipe) id: number) {
    const user = await this.userService.findById(id);
    if (!user) throw new NotFoundException();
    return user;
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseFilters(HttpExceptionFilter)
  @Get()
  async getUserByName(@Param('email') email: string) {
    const user = await this.userService.findByUserEmail(email);
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
