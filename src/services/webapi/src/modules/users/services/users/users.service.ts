import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from 'src/typeorm';
import { encodePassword } from 'src/utils/bcrypt';
import { Repository } from 'typeorm';
import { CreateUserDto } from '../../dto/CreateUser.dto';
import { UserListDto } from '../../dto/UserList.dto';

@Injectable()
export class UsersService {
  constructor(
    @InjectRepository(User)
    private readonly repository: Repository<User>,
  ) {}
  async getUsers() {
    const data = await this.repository.find();
    return data.map((user) => new UserListDto(user));
  }
  createUser(createUserDto: CreateUserDto) {
    const password = encodePassword(createUserDto.password);
    const user = this.repository.create({ ...createUserDto, password });
    return this.repository.save(user);
  }
  async findByUserName(userName: string) {
    const data = await this.repository.findOne({ where: { userName } });
    return new UserListDto(data);
  }
  async findById(id: number) {
    const data = await this.repository.findOne({ where: { id } });
    return new UserListDto(data);
  }
}
