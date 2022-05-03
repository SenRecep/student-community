import { Inject, Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { UsersService } from 'src/modules/users/services/users/users.service';
import { comparePassword } from 'src/utils/bcrypt';

@Injectable()
export class AuthService {
  constructor(
    @Inject('UsersService') private readonly usersService: UsersService,
    @Inject(JwtService) private readonly jwtService: JwtService,
  ) {}

  async validateUser(username: string, password: string) {
    const userDb = await this.usersService.findByUserName(username);
    if (!userDb) return null;
    const matched = comparePassword(password, userDb.password);
    if (!matched) return null;
    return userDb;
  }
  async login(user: any) {
    const payload = { username: user.userName, sub: user.id };
    return {
      access_token: this.jwtService.sign(payload),
    };
  }
}
