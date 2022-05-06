import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Address, Post } from 'src/typeorm';
import { Repository } from 'typeorm';
import { PostCreateDto } from '../../dto/PostCreate.dto';

@Injectable()
export class PostsService {
  constructor(
    @InjectRepository(Post)
    private readonly postsRepository: Repository<Post>,
    @InjectRepository(Address)
    private readonly addressRepository: Repository<Address>,
  ) {}
  async getByIdAsync(id: number) {
    return await this.postsRepository.findOne({
      where: { id },
      relations: ['address'],
    });
  }
  async getAllAsync() {
    return await this.postsRepository.find({ relations: ['address'] });
  }
  async createAsync(postCreateDto: PostCreateDto) {
    const address = this.addressRepository.create(postCreateDto.address);
    await this.addressRepository.save(address);
    postCreateDto.address = address;
    const post = this.postsRepository.create(postCreateDto);
    return await this.postsRepository.save(post);
  }
}
