import { User } from './User.entity';
import { SessionEntity } from './Session.entity';
import { Post } from './Post.entity';
import { Address } from './Address.entity';
export { User };
export { SessionEntity };
export { Post };
export { Address };

const entities = [User, SessionEntity, Post, Address];

export default entities;
