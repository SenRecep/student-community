import { Inject, Injectable } from '@nestjs/common';
import { UsersService } from 'src/modules/users/services/users/users.service';
import { comparePassword } from 'src/utils/bcrypt';

@Injectable()
export class AuthService {
  constructor(
    @Inject('UsersService') private readonly usersService: UsersService,
  ) {}

  async validateUser(username: string, password: string) {
    const userDb = await this.usersService.findByName(username);
    if (!userDb) return null;
    const matched = comparePassword(password, userDb.password);
    if (!matched) return null;
    return userDb;
  }
}
