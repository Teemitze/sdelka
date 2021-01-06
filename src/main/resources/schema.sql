DROP TABLE IF EXISTS adverts;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS passwords;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    city  VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS passwords
(
    id       SERIAL PRIMARY KEY,
    password VARCHAR NOT NULL,
    user_id  INTEGER REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS roles
(
    id      SERIAL PRIMARY KEY,
    role    VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS categories
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL UNIQUE,
    parent_id INTEGER REFERENCES categories (id)
);


CREATE TABLE IF NOT EXISTS adverts
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