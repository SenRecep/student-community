import { Inject } from '@nestjs/common';
import { PassportSerializer } from '@nestjs/passport';
import { UsersService } from 'src/modules/users/services/users/users.service';
import { User } from 'src/typeorm';

export class SessionSerializer extends PassportSerializer {
  constructor(
    @Inject('UsersService') private readonly usersService: UsersService,
  ) {
    super();
  }

  serializeUser(user: User, done: (err, user: User) => void) {
    done(null, user);
  }
  async deserializeUser(user: User, done: (err, user: User) => void) {
    const found = await this.usersService.findById(user.id);
    done(null, found);
  }
}
