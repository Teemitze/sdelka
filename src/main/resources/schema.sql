DROP TABLE IF EXISTS adverts;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    city  VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
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