import {
  Body,
  ClassSerializerInterceptor,
  Controller,
  Get,
  Inject,
  Post,
  Req,
  Session,
  UseGuards,
  UseInterceptors,
} from '@nestjs/common';
import { Request } from 'express';
import {
  LocalGuard,
  AuthanticatedGuard,
} from 'src/modules/auth/guards/Local.guard';
import { UserListDto } from 'src/modules/users/dto/UserList.dto';
import { Public } from '../../guards/Public.guard';
import { AuthService } from '../../services/auth/auth.service';
import { Introspec } from '../../types/Introspec';
import { JwtToken } from '../../types/JwtToken';

@Controller('auth')
export class AuthController {
  constructor(
    @Inject('AuthService') private readonly authService: AuthService,
  ) {}
  @Public()
  @UseGuards(LocalGuard)
  @Post('login')
  async login(@Req() req: Request) {
    return this.authService.login(req.user);
  }
  @Public()
  @Post('verify')
  verify(@Body() jwtToken: JwtToken) {
    const value = this.authService.verify(jwtToken);
    return new Introspec(value);
  }

  @Get('')
  getAuthSession(@Session() session: Record<string, any>) {
    session.authanticated = true;
    return session;
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseGuards(AuthanticatedGuard)
  @Get('status')
  getAuthStatus(@Req() req: Request) {
    return new UserListDto(req.user);
  }
}
