import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Address, Post } from 'src/typeorm';
import { Repository } from 'typeorm';
import { PostCreateDto } from '../../dto/PostCreate.dto';
import { PostUpdateDto } from '../../dto/PostUpdate.dto';

@Injectable()
export class PostsService {
  constructor(
    @InjectRepository(Post)
    private readonly postsRepository: Repository<Post>,
    @InjectRepository(Address)
    private readonly addressRepository: Repository<Address>,
  ) {}
  getByIdAsync(id: number): Promise<Post> {
    return this.postsRepository.findOne({
      where: { id },
      relations: ['address'],
    });
  }
  getAllAsync(): Promise<Post[]> {
    return this.postsRepository.find({ relations: ['address'] });
  }
  getAllWhitoutDeleted(): Promise<Post[]> {
    return this.postsRepository.find({
      where: { isDeleted: false },
      relations: ['address'],
    });
  }
  async getAllPager(take = 5, skip = 0): Promise<Post[]> {
    const [data] = await this.postsRepository.findAndCount({
      take,
      skip,
      relations: ['address'],
      where: { isDeleted: false },
      order: { updatedTime: 'DESC' },
    });
    return data;
  }

  async deleteAsync(id: number): Promise<Post> {
    const found = await this.getByIdAsync(id);
    this.postsRepository.update({ id }, { isDeleted: true });
    this.addressRepository.update(
      { id: found.address.id },
      { isDeleted: true },
    );
    return await this.getByIdAsync(id);
  }
  async updateAsync(id: number, postUpdateDto: PostUpdateDto): Promise<Post> {
    const found = await this.getByIdAsync(id);
    if (!found) throw new NotFoundException('Post not found');
    for (const key in postUpdateDto) {
      if (
        Object.prototype.hasOwnProperty.call(found, key) &&
        postUpdateDto[key] &&
        typeof postUpdateDto[key] !== 'object'
      ) {
        found[key] = postUpdateDto[key];
      }
    }
    if (postUpdateDto.address) {
      for (const key in postUpdateDto.address) {
        if (
          Object.prototype.hasOwnProperty.call(found.address, key) &&
          postUpdateDto.address[key]
        ) {
          found.address[key] = postUpdateDto.address[key];
        }
      }
    }
    await this.addressRepository.update(
      { id: found.address.id },
      { ...found.address },
    );
    await this.postsRepository.update({ id }, { ...found });
    return found;
  }

  async createAsync(postCreateDto: PostCreateDto): Promise<Post> {
    const address = this.addressRepository.create(postCreateDto.address);
    await this.addressRepository.save(address);
    postCreateDto.address = address;
    const post = this.postsRepository.create(postCreateDto);
    return await this.postsRepository.save(post);
  }
}
