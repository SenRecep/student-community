import { BadRequestException, Injectable } from '@nestjs/common';
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
  async createUser(createUserDto: CreateUserDto) {
    const isExist = await this.findByUserEmail(createUserDto.email);
    if (isExist) throw new BadRequestException('Email already exist');
    const password = encodePassword(createUserDto.password);
    const user = this.repository.create({ ...createUserDto, password });
    return await this.repository.save(user);
  }
  async findByUserEmail(email: string) {
    const data = await this.repository.findOne({ where: { email } });
    if (!data) return null;
    return new UserListDto(data);
  }
  async findById(id: number) {
    const data = await this.repository.findOne({ where: { id } });
    if (!data) return null;
    return new UserListDto(data);
  }
}
