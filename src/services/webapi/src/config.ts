import { EnviormentKeys } from './constants/EnviormentKeys';

export const config = () => {
  const cnf = {
    PORT: +process.env[EnviormentKeys.PORT] || 3000,
    PUBLIC_KEY: process.env[EnviormentKeys.PUBLIC_KEY] || 'myPublicKey',
    SESSION: {
      SECRET: process.env[EnviormentKeys.SESSION_SECRET] || 'mysecretkey',
      MAXAGE: +process.env[EnviormentKeys.SESSION_MAXAGE] || 60000,
    },
    JWT: {
      SECRET: process.env[EnviormentKeys.JWT_SECRET] || 'mysecretkey',
      EXPIRES_IN: +process.env[EnviormentKeys.JWT_EXPIRES_IN] || '7d',
    },
    DB: {
      HOST: process.env[EnviormentKeys.DB_HOST],
      PORT: +process.env[EnviormentKeys.DB_PORT],
      USER: process.env[EnviormentKeys.DB_USERNAME],
      PASSWORD: process.env[EnviormentKeys.DB_PASSWORD],
      DATABASE: process.env[EnviormentKeys.DB_NAME],
    },
  };
  return cnf;
};
