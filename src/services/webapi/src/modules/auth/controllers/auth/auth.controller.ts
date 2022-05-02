import { Controller, Get, Post, Req, Session, UseGuards } from '@nestjs/common';
import { Request } from 'express';
import {
  LocalGuard,
  AuthanticatedGuard,
} from 'src/modules/auth/utils/LocalGuard';

@Controller('auth')
export class AuthController {
  @UseGuards(LocalGuard)
  @Post('login')
  async login() {
    console.log('login');
  }

  @Get('')
  getAuthSession(@Session() session: Record<string, any>) {
    session.authanticated = true;
    return session;
  }

  @UseGuards(AuthanticatedGuard)
  @Get('status')
  getAuthStatus(@Req() req: Request) {
    return req.user;
  }
}
