import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import * as session from 'express-session';
import * as passport from 'passport';
import { TypeormStore } from 'connect-typeorm';
import { getRepository } from 'typeorm';
import { SessionEntity } from './typeorm';
import { ConfigService } from '@nestjs/config';
import { EnviormentKeys } from './constants/EnviormentKeys';

const bootstrap = async () => {
  const app = await NestFactory.create(AppModule);
  const configService = app.get(ConfigService);
  const port = +configService.get(EnviormentKeys.PORT);
  const sessionSecret = configService.get(EnviormentKeys.SESSION_SECRET);
  const sessionMaxAge = +configService.get(EnviormentKeys.SESSION_MAXAGE);
  const sessionRepository = getRepository(SessionEntity);
  app.setGlobalPrefix('api');
  app.use(
    session({
      name: 'NESTJS_SESSION',
      secret: sessionSecret,
      resave: false,
      saveUninitialized: false,
      cookie: {
        maxAge: sessionMaxAge,
      },
      store: new TypeormStore().connect(sessionRepository),
    }),
  );
  app.use(passport.initialize());
  app.use(passport.session());
  await app.listen(port);
};

bootstrap();
