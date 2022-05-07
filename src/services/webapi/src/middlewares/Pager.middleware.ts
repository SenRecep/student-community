import { Injectable, NestMiddleware } from '@nestjs/common';
import { NextFunction, Response } from 'express';

@Injectable()
export class PagerMiddleware implements NestMiddleware {
  use(req: any, res: Response, next: NextFunction) {
    req.query.take = +req.query.take || 5;
    req.query.skip = +req.query.skip || 0;
    next();
  }
}
