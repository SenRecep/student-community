import { SetMetadata } from '@nestjs/common';
import { PublicKey } from '../constants';

export const Public = () => SetMetadata(PublicKey.key, true);
