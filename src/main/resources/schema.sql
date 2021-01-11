DROP TABLE IF EXISTS adverts CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    phone    VARCHAR NOT NULL UNIQUE,
    city     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    enabled  BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE users_role
(
    user_id INTEGER REFERENCES users (id),
    role_id INTEGER REFERENCES roles (id)
);

CREATE TABLE categories
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL UNIQUE,
    parent_id INTEGER REFERENCES categories (id)
);

CREATE TABLE adverts
(
    uuid        UUID PRIMARY KEY,
    name        VARCHAR NOT NULL,
    description TEXT,
    price       BIGINT,
    city        VARCHAR,
    create_data DATE    NOT NULL,
    is_active   boolean NOT NULL,
    is_new      boolean NOT NULL,
    photo_path  VARCHAR[],
    category_id INTEGER REFERENCES categories (id),
    user_id     INTEGER REFERENCES users (id)
);