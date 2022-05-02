import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from 'src/typeorm';
import { encodePassword } from 'src/utils/bcrypt';
import { Repository } from 'typeorm';
import { CreateUserDto } from '../../dto/CreateUser.dto';

@Injectable()
export class UsersService {
  constructor(
    @InjectRepository(User)
    private readonly repository: Repository<User>,
  ) {}
  getUsers() {
    return this.repository.find();
  }
  createUser(createUserDto: CreateUserDto) {
    const password = encodePassword(createUserDto.password);
    const user = this.repository.create({ ...createUserDto, password });
    return this.repository.save(user);
  }
  findByName(name: string) {
    return this.repository.findOne({ where: { name } });
  }
  findById(id: number) {
    return this.repository.findOne({ where: { id } });
  }
}
